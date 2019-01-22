package com.niocoder.beans.factory.xml;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.ConstructorArgument;
import com.niocoder.beans.PropertyValue;
import com.niocoder.beans.factory.BeanDefinitionStoreException;
import com.niocoder.beans.factory.config.RuntimeBeanReference;
import com.niocoder.beans.factory.config.TypedStringValue;
import com.niocoder.beans.factory.support.BeanDefinitionRegistry;
import com.niocoder.beans.factory.support.GenericBeanDefinition;
import com.niocoder.core.io.Resource;
import com.niocoder.util.StringUtils;
import lombok.extern.java.Log;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Iterator;

/**
 * @author zhenglongfei
 */
@Log
public class XmlBeanDefinitionReader {
    /**
     * <bean id = "nioCoder"
     * class = "com.niocoder.service.v1.NioCoderService">
     * </bean>
     * <property name ="accountDao" ref="accountDao"></property>
     */

    private static final String ID_ATTRIBUTE = "id";

    private static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";

    public static final String TYPE_ATTRIBUTE = "type";
    BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 具体解析bean.xml的方法 使用dom4j
     *
     * @param resource
     */
    public void loadBeanDefinition(Resource resource) {
        try (InputStream is = resource.getInputStream()) {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);

            Element root = doc.getRootElement();
            Iterator<Element> elementIterator = root.elementIterator();
            while (elementIterator.hasNext()) {
                Element ele = elementIterator.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                if (ele.attribute(SCOPE_ATTRIBUTE) != null) {
                    bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));
                }
                parseConstructorArgElements(ele, bd);
                parsePropertyElement(ele, bd);
                this.registry.registerBeanDefinition(id, bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document", e);
        }
    }

    private void parseConstructorArgElements(Element ele, BeanDefinition bd) {
        Iterator iterator = ele.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            parseConstructorArgElement(element, bd);
        }
    }

    private void parseConstructorArgElement(Element element, BeanDefinition bd) {
        String typeAttr = element.attributeValue(TYPE_ATTRIBUTE);
        String nameAttr = element.attributeValue(NAME_ATTRIBUTE);
        Object value = parsePropertyValue(element, bd, null);
        ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
        if (StringUtils.hasLength(typeAttr)) {
            valueHolder.setType(typeAttr);
        }
        if (StringUtils.hasLength(nameAttr)) {
            valueHolder.setName(nameAttr);
        }

        bd.getConstructorArgument().addArgumentValue(valueHolder);
    }

    private void parsePropertyElement(Element ele, BeanDefinition bd) {
        Iterator iterator = ele.elementIterator(PROPERTY_ELEMENT);
        while (iterator.hasNext()) {
            Element propElem = (Element) iterator.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                log.info("Tag 'property' must have a 'name' attribute");
                return;
            }

            Object val = parsePropertyValue(propElem, bd, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, val);
            bd.getPropertyValues().add(pv);
        }
    }

    private Object parsePropertyValue(Element propElem, BeanDefinition bd, String propertyName) {

        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";

        boolean hasRefAttribute = (propElem.attribute(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (propElem.attribute(VALUE_ATTRIBUTE) != null);

        if (hasRefAttribute) {
            String refName = propElem.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                log.info(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        } else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(propElem.attributeValue(VALUE_ATTRIBUTE));
            return valueHolder;
        } else {
            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }
}

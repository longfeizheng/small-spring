package com.niocoder.test.v3;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.ConstructorArgument;
import com.niocoder.beans.factory.config.RuntimeBeanReference;
import com.niocoder.beans.factory.config.TypedStringValue;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 *
 */
public class BeanDefinitionTestV3 {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("bean-v3.xml"));
    }

    @Test
    public void testConstructorArgument() {

        BeanDefinition bd = factory.getBeanDefinition("nioCoder");
        Assert.assertEquals("com.niocoder.service.v3.NioCoderService", bd.getBeanClassName());

        ConstructorArgument args = bd.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> valueHolders = args.getArgumentValues();

        Assert.assertEquals(3, valueHolders.size());

        RuntimeBeanReference ref1 = (RuntimeBeanReference) valueHolders.get(0).getValue();
        Assert.assertEquals("accountDao", ref1.getBeanName());

        RuntimeBeanReference ref2 = (RuntimeBeanReference) valueHolders.get(1).getValue();
        Assert.assertEquals("itemDao", ref2.getBeanName());

        TypedStringValue stringValue = (TypedStringValue) valueHolders.get(2).getValue();
        Assert.assertEquals("1", stringValue.getValue());
    }
}

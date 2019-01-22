package com.niocoder.test.v2;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.PropertyValue;
import com.niocoder.beans.factory.config.RuntimeBeanReference;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 测试 property标签
 * <property name ="itemDao" ref="itemDao"></property>
 * <property name ="url" value="http://niocoder.com/"></property>
 */
public class BeanDefinitionTestV2 {


    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("bean-v2.xml"));
    }

    @Test
    public void testGetBeanDefinition() throws Exception {
        BeanDefinition bd = factory.getBeanDefinition("nioCoder");

        List<PropertyValue> pvs = bd.getPropertyValues();

        Assert.assertTrue(pvs.size() == 6);

        {
            PropertyValue pv = this.getPropertyValues("accountDao", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }

        {
            PropertyValue pv = this.getPropertyValues("itemDao", pvs);
            Assert.assertNotNull(pv);
            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getPropertyValues(String name, List<PropertyValue> pvs) {
        for (PropertyValue pv : pvs) {
            if (pv.getName().equals(name)) {
                return pv;
            }
        }
        return null;
    }
}

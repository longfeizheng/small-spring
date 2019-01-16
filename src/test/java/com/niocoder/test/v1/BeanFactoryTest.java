package com.niocoder.test.v1;

import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.factory.BeanCreationException;
import com.niocoder.beans.factory.BeanDefinitionStoreException;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.service.v1.NioCoderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * BeanFactory 测试类
 */
public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    /**
     * 测试获取bean
     */
    @Test
    public void testGetBean() {

        reader.loadBeanDefinition(new ClassPathResource("bean-v1.xml"));

        BeanDefinition bd = factory.getBeanDefinition("nioCoder");

        assertEquals("com.niocoder.service.v1.NioCoderService", bd.getBeanClassName());

        NioCoderService nioCoderService = (NioCoderService) factory.getBean("nioCoder");

        assertNotNull(nioCoderService);
    }

    /**
     * 测试无效的bean
     */
    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinition(new ClassPathResource("bean-v1.xml"));
        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }

        Assert.fail("expect BeanCreationException ");
    }

    /**
     * 测试无效的xml
     */
    @Test
    public void testInvalidXML() {
        try {
            reader.loadBeanDefinition(new ClassPathResource("xxx-v1.xml"));
        } catch (BeanDefinitionStoreException e) {
            return;
        }

        Assert.fail("expect BeanDefinitionStoreException ");
    }
}

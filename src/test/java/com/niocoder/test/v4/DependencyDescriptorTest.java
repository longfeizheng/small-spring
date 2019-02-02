package com.niocoder.test.v4;

import com.niocoder.beans.factory.config.DependencyDescriptor;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.dao.v4.AccountDao;
import com.niocoder.service.v4.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 测试DependencyDescriptor
 */
public class DependencyDescriptorTest {

    @Test
    public void testResolveDependency() throws Exception {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("bean-v4.xml"));

        Field f = NioCoderService.class.getDeclaredField("accountDao");
        DependencyDescriptor descriptor = new DependencyDescriptor(f, true);
        Object o = factory.resolveDependency(descriptor);
        Assert.assertTrue(o instanceof AccountDao);

    }
}
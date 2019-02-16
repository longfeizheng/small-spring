package com.niocoder.test.v5;

import com.niocoder.aop.config.MethodLocatingFactory;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.tx.TransactionManager;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created on 2019/2/16.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class MethodLocatingFactoryTest {

    @Test
    public void testGetMethod() throws Exception {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("bean-v5.xml"));

        MethodLocatingFactory methodLocatingFactory = new MethodLocatingFactory();
        methodLocatingFactory.setTargetBeanName("tx");
        methodLocatingFactory.setMethodName("start");
        methodLocatingFactory.setBeanFactory(factory);

        Method start = methodLocatingFactory.getObject();

        Assert.assertTrue(TransactionManager.class.equals(start.getDeclaringClass()));
        Assert.assertTrue(start.equals(TransactionManager.class.getMethod("start")));

        TransactionManager tx = (TransactionManager) factory.getBean("tx");
        start.invoke(tx);
    }
}

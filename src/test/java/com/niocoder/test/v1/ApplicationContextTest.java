package com.niocoder.test.v1;

import com.niocoder.context.ApplicationContext;
import com.niocoder.context.support.ClassPathXmlApplicationContext;
import com.niocoder.context.support.FileSystemXmlApplicationContext;
import com.niocoder.service.v1.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

/**
 * ApplicationContext 测试类
 */
public class ApplicationContextTest {

    @Test
    public void testGetBeanFromClassPathContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-v1.xml");
        NioCoderService nioCoderService = (NioCoderService) context.getBean("nioCoder");
        Assert.assertNotNull(nioCoderService);
    }

    @Test
    public void testGetBeanFromFileSystemContext() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/test/resources/bean-v1.xml");
        NioCoderService nioCoderService = (NioCoderService) context.getBean("nioCoder");
        Assert.assertNotNull(nioCoderService);
    }
}

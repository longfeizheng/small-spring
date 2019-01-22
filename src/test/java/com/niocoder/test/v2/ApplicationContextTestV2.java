package com.niocoder.test.v2;

import com.niocoder.context.ApplicationContext;
import com.niocoder.context.support.ClassPathXmlApplicationContext;
import com.niocoder.dao.v2.AccountDao;
import com.niocoder.dao.v2.ItemDao;
import com.niocoder.service.v2.NioCoderService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApplicationContextTestV2 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-v2.xml");
        NioCoderService nioCoderService = (NioCoderService) ctx.getBean("nioCoder");

        Assert.assertNotNull(nioCoderService.getAccountDao());
        Assert.assertNotNull(nioCoderService.getItemDao());
        Assert.assertNotNull(nioCoderService.getUrl());
        Assert.assertNotNull(nioCoderService.getBirthday());
        Assert.assertNotNull(nioCoderService.getFlag());
        Assert.assertNotNull(nioCoderService.getVersion());

        assertTrue(nioCoderService.getItemDao() instanceof ItemDao);
        assertTrue(nioCoderService.getAccountDao() instanceof AccountDao);
        assertEquals(nioCoderService.getUrl(), "http://niocoder.com/");
        assertTrue(nioCoderService.getBirthday() instanceof Date);
        assertTrue(nioCoderService.getFlag());
        assertEquals(nioCoderService.getVersion(), new Integer(1));

    }
}

package com.niocoder.test.v6;

import com.niocoder.context.ApplicationContext;
import com.niocoder.context.support.ClassPathXmlApplicationContext;
import com.niocoder.service.v6.INioCoderService;
import com.niocoder.util.MessageTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ApplicationContextTest {
    @Before
    public void setUp() {
        MessageTracker.clearMsgs();
    }

    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-v6.xml");
        INioCoderService nioCoderService = (INioCoderService) ctx.getBean("nioCoder");

        nioCoderService.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();

        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit tx", msgs.get(2));
    }
}

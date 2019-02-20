package com.niocoder.test.v5;

import com.niocoder.context.ApplicationContext;
import com.niocoder.context.support.ClassPathXmlApplicationContext;
import com.niocoder.service.v5.NioCoderService;
import com.niocoder.util.MessageTracker;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 *
 */
public class ApplicationContextTest {

    @Test
    public void testApplicationContext() throws Exception {

        MessageTracker.clearMsgs();

        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean-v5.xml");
        NioCoderService nioCoder = (NioCoderService) ctx.getBean("nioCoder");

        Assert.assertNotNull(nioCoder.getAccountDao());
        Assert.assertNotNull(nioCoder.getItemDao());

        nioCoder.placeOrder();

        //
        List<String> msgs = MessageTracker.getMsgs();

        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit tx", msgs.get(2));
    }
}

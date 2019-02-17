package com.niocoder.test.v5;

import com.niocoder.aop.aspectj.AspectJAfterReturningAdvice;
import com.niocoder.aop.aspectj.AspectJAfterThrowingAdvice;
import com.niocoder.aop.aspectj.AspectJBeforeAdvice;
import com.niocoder.aop.framework.ReflectiveMethodInvocation;
import com.niocoder.service.v5.NioCoderService;
import com.niocoder.tx.TransactionManager;
import com.niocoder.util.MessageTracker;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/2/17.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class ReflectiveMethodInvocationTest {

    private AspectJBeforeAdvice beforeAdvice = null;
    private AspectJAfterReturningAdvice afterReturningAdvice;
    private AspectJAfterThrowingAdvice afterThrowingAdvice;
    private NioCoderService nioCoderService;
    private TransactionManager tx;


    @Before
    public void setUp() throws Exception {
        nioCoderService = new NioCoderService();
        tx = new TransactionManager();
        MessageTracker.clearMsgs();
        beforeAdvice = new AspectJBeforeAdvice(TransactionManager.class.getMethod("start"), null, tx);
        afterReturningAdvice = new AspectJAfterReturningAdvice(TransactionManager.class.getMethod("commit"), null, tx);
        afterThrowingAdvice = new AspectJAfterThrowingAdvice(TransactionManager.class.getMethod("rollback"), null, tx);
    }


    @Test
    public void textMethodInvocation() throws Throwable {

        Method targetMethod = NioCoderService.class.getMethod("placeOrder");
        List<MethodInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(beforeAdvice);
        interceptorList.add(afterReturningAdvice);

        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(nioCoderService, targetMethod, new Object[0], interceptorList);
        mi.proceed();

        List<String> msgs = MessageTracker.getMsgs();

        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit tx", msgs.get(2));
    }

    @Test
    public void testAfterThrowing() throws Throwable{

        Method targetMethod = NioCoderService.class.getMethod("placeOrderWithException");
        List<MethodInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(beforeAdvice);
        interceptorList.add(afterThrowingAdvice);

        ReflectiveMethodInvocation mi = new ReflectiveMethodInvocation(nioCoderService, targetMethod, new Object[0], interceptorList);

        try {
            mi.proceed();
        } catch (Throwable t) {
            List<String> msgs = MessageTracker.getMsgs();

            Assert.assertEquals(2, msgs.size());
            Assert.assertEquals("start tx", msgs.get(0));
            Assert.assertEquals("rollback tx", msgs.get(1));
            return;
        }

        Assert.fail("No Exception thrown");
    }
}

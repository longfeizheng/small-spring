package com.niocoder.test.v5;

import com.niocoder.aop.aspectj.AspectJAfterReturningAdvice;
import com.niocoder.aop.aspectj.AspectJBeforeAdvice;
import com.niocoder.aop.aspectj.AspectJExpressionPointcut;
import com.niocoder.aop.framework.AopConfig;
import com.niocoder.aop.framework.AopConfigSupport;
import com.niocoder.aop.framework.CglibProxyFactory;
import com.niocoder.service.v5.NioCoderService;
import com.niocoder.tx.TransactionManager;
import com.niocoder.util.MessageTracker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created on 2019/2/17.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class CglibAopProxyTest {

    private static AspectJBeforeAdvice beforeAdvice = null;
    private static AspectJAfterReturningAdvice afterAdvice = null;
    private AspectJExpressionPointcut pc = null;
    private NioCoderService nioCoderService = null;
    private TransactionManager tx;

    @Before
    public void setUp() throws Exception {
        nioCoderService = new NioCoderService();
        tx = new TransactionManager();
        String expression = "execution(* com.niocoder.service.v5.*.placeOrder(..))";
        pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        beforeAdvice = new AspectJBeforeAdvice(TransactionManager.class.getMethod("start"), pc, tx);

        afterAdvice = new AspectJAfterReturningAdvice(TransactionManager.class.getMethod("commit"), pc, tx);
    }

    @Test
    public void testGetProxy(){
        AopConfig config = new AopConfigSupport();

        config.addAdvice(beforeAdvice);
        config.addAdvice(afterAdvice);
        config.setTargetObject(nioCoderService);

        CglibProxyFactory proxyFactory = new CglibProxyFactory(config);

        NioCoderService proxy = (NioCoderService)proxyFactory.getProxy();

        proxy.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();

        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit tx", msgs.get(2));
    }
}

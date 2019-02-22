package com.niocoder.test.v5;

import com.niocoder.aop.aspectj.AspectJAfterReturningAdvice;
import com.niocoder.aop.aspectj.AspectJBeforeAdvice;
import com.niocoder.aop.aspectj.AspectJExpressionPointcut;
import com.niocoder.aop.config.AspectInstanceFactory;
import com.niocoder.aop.framework.AopConfig;
import com.niocoder.aop.framework.AopConfigSupport;
import com.niocoder.aop.framework.CglibProxyFactory;
import com.niocoder.beans.factory.BeanFactory;
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
public class CglibAopProxyTest extends AbstractV5Test {

    private static AspectJBeforeAdvice beforeAdvice = null;
    private static AspectJAfterReturningAdvice afterAdvice = null;
    private AspectJExpressionPointcut pc = null;
    private BeanFactory beanFactory = null;
    private NioCoderService nioCoderService = null;
    private AspectInstanceFactory aspectInstanceFactory = null;

    @Before
    public void setUp() throws Exception {

        MessageTracker.clearMsgs();

        String expression = "execution(* com.niocoder.service.v5.*.placeOrder(..))";
        pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);
        nioCoderService = new NioCoderService();

        beanFactory = this.getBeanFactory("bean-v5.xml");
        aspectInstanceFactory = this.getAspectInstanceFactory("tx");
        aspectInstanceFactory.setBeanFactory(beanFactory);

        beforeAdvice = new AspectJBeforeAdvice(
                getAdviceMethod("start"),
                pc,
                aspectInstanceFactory);

        afterAdvice = new AspectJAfterReturningAdvice(
                getAdviceMethod("commit"),
                pc,
                aspectInstanceFactory);
    }

    @Test
    public void testGetProxy() {
        AopConfig config = new AopConfigSupport();

        config.addAdvice(beforeAdvice);
        config.addAdvice(afterAdvice);
        config.setTargetObject(nioCoderService);

        CglibProxyFactory proxyFactory = new CglibProxyFactory(config);

        NioCoderService proxy = (NioCoderService) proxyFactory.getProxy();

        proxy.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();

        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start tx", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit tx", msgs.get(2));
    }
}

package com.niocoder.test.v5;

import com.niocoder.aop.MethodMatcher;
import com.niocoder.aop.aspectj.AspectJExpressionPointcut;
import com.niocoder.service.v5.NioCoderService;
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
public class PointcutTest {

    @Test
    public void testPointCutTest() throws Exception {
        String expression = "execution(* com.niocoder.service.v5.*.placeOrder(..))";
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        MethodMatcher mm = pc.getMethodMatcher();

        {
            Class<?> targetClass = NioCoderService.class;

            Method placeOrder = targetClass.getMethod("placeOrder");
            Assert.assertTrue(mm.matches(placeOrder));

            Method getAccountDao = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(getAccountDao));
        }

        {
            Class<?> targetClass = com.niocoder.service.v4.NioCoderService.class;
            Method placeOrder = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(placeOrder));
        }
    }
}

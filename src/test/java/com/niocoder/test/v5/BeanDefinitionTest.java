package com.niocoder.test.v5;

import com.niocoder.aop.aspectj.AspectJAfterReturningAdvice;
import com.niocoder.aop.aspectj.AspectJAfterThrowingAdvice;
import com.niocoder.aop.aspectj.AspectJBeforeAdvice;
import com.niocoder.aop.aspectj.AspectJExpressionPointcut;
import com.niocoder.aop.config.AspectInstanceFactory;
import com.niocoder.aop.config.MethodLocatingFactory;
import com.niocoder.beans.BeanDefinition;
import com.niocoder.beans.ConstructorArgument;
import com.niocoder.beans.PropertyValue;
import com.niocoder.beans.factory.config.RuntimeBeanReference;
import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.tx.TransactionManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BeanDefinitionTest extends AbstractV5Test {

    @Test
    public void testAOPBean() throws Exception {

        DefaultBeanFactory factory = (DefaultBeanFactory) this.getBeanFactory("bean-v5.xml");

        // 检查名称为 tx 的 Bean 是否生成
        {
            BeanDefinition bd = factory.getBeanDefinition("tx");
            Assert.assertTrue(bd.getBeanClassName().equalsIgnoreCase(TransactionManager.class.getName()));
        }

        // 检查placeOrder 是否正确生成
        {
            BeanDefinition bd = factory.getBeanDefinition("placeOrder");
            // 这个BeanDefinition 是合成的bean
            Assert.assertTrue(bd.isSynthetic());
            Assert.assertTrue(bd.getBeanClass().equals(AspectJExpressionPointcut.class));

            PropertyValue pv = bd.getPropertyValues().get(0);
            Assert.assertEquals("expression", pv.getName());
            Assert.assertEquals("execution(* com.niocoder.service.v5.*.placeOrder(..))", pv.getValue());
        }

        // 检查AspectJBeforeAdvice
        {
            String name = AspectJBeforeAdvice.class.getName() + "#0";
            BeanDefinition bd = factory.getBeanDefinition(name);
            Assert.assertTrue(bd.getBeanClass().equals(AspectJBeforeAdvice.class));

            // 这个BeanDefinition是合成的
            Assert.assertTrue(bd.isSynthetic());

            List<ConstructorArgument.ValueHolder> argumentValues = bd.getConstructorArgument().getArgumentValues();
            Assert.assertEquals(3, argumentValues.size());

            // 构造函数第一个参数
            {
                BeanDefinition innerBeanDef = (BeanDefinition) argumentValues.get(0).getValue();
                Assert.assertTrue(innerBeanDef.isSynthetic());
                Assert.assertTrue(innerBeanDef.getBeanClass().equals(MethodLocatingFactory.class));

                List<PropertyValue> pvs = innerBeanDef.getPropertyValues();
                Assert.assertEquals("targetBeanName", pvs.get(0).getName());
                Assert.assertEquals("tx", pvs.get(0).getValue());
                Assert.assertEquals("methodName", pvs.get(1).getName());
                Assert.assertEquals("start", pvs.get(1).getValue());
            }

            // 构造函数第二个参数
            {
                RuntimeBeanReference ref = (RuntimeBeanReference) argumentValues.get(1).getValue();
                Assert.assertEquals("placeOrder", ref.getBeanName());
            }
            chekValueHolder(argumentValues);


        }

        // 检查AspectJAfterReturningAdvice
        {
            String name = AspectJAfterReturningAdvice.class.getName() + "#0";
            BeanDefinition bd = factory.getBeanDefinition(name);
            Assert.assertTrue(bd.getBeanClass().equals(AspectJAfterReturningAdvice.class));

            // 这个BeanDefinition是合成的
            Assert.assertTrue(bd.isSynthetic());

            List<ConstructorArgument.ValueHolder> argumentValues = bd.getConstructorArgument().getArgumentValues();
            Assert.assertEquals(3, argumentValues.size());

            // 构造函数第一个参数
            {
                BeanDefinition innerBeanDef = (BeanDefinition) argumentValues.get(0).getValue();
                Assert.assertTrue(innerBeanDef.isSynthetic());
                Assert.assertTrue(innerBeanDef.getBeanClass().equals(MethodLocatingFactory.class));

                List<PropertyValue> pvs = innerBeanDef.getPropertyValues();
                Assert.assertEquals("targetBeanName", pvs.get(0).getName());
                Assert.assertEquals("tx", pvs.get(0).getValue());
                Assert.assertEquals("methodName", pvs.get(1).getName());
                Assert.assertEquals("commit", pvs.get(1).getValue());
            }

            // 构造函数第二个参数
            {
                RuntimeBeanReference ref = (RuntimeBeanReference) argumentValues.get(1).getValue();
                Assert.assertEquals("placeOrder", ref.getBeanName());
            }

            // 构造函数第三个参数
            chekValueHolder(argumentValues);
        }
        // 检查AspectJAfterThrowingAdvice
        {
            String name = AspectJAfterThrowingAdvice.class.getName() + "#0";
            BeanDefinition bd = factory.getBeanDefinition(name);
            Assert.assertTrue(bd.getBeanClass().equals(AspectJAfterThrowingAdvice.class));

            // 这个BeanDefinition是合成的
            Assert.assertTrue(bd.isSynthetic());

            List<ConstructorArgument.ValueHolder> argumentValues = bd.getConstructorArgument().getArgumentValues();
            Assert.assertEquals(3, argumentValues.size());

            // 构造函数第一个参数
            {
                BeanDefinition innerBeanDef = (BeanDefinition) argumentValues.get(0).getValue();
                Assert.assertTrue(innerBeanDef.isSynthetic());
                Assert.assertTrue(innerBeanDef.getBeanClass().equals(MethodLocatingFactory.class));

                List<PropertyValue> pvs = innerBeanDef.getPropertyValues();
                Assert.assertEquals("targetBeanName", pvs.get(0).getName());
                Assert.assertEquals("tx", pvs.get(0).getValue());
                Assert.assertEquals("methodName", pvs.get(1).getName());
                Assert.assertEquals("rollback", pvs.get(1).getValue());
            }

            // 构造函数第二个参数
            {
                RuntimeBeanReference ref = (RuntimeBeanReference) argumentValues.get(1).getValue();
                Assert.assertEquals("placeOrder", ref.getBeanName());
            }

            // 构造函数第三个参数
            chekValueHolder(argumentValues);
        }

    }

    private void chekValueHolder(List<ConstructorArgument.ValueHolder> argumentValues) {
        // 构造函数第三个参数
        {
            BeanDefinition innerBeanDef = (BeanDefinition) argumentValues.get(2).getValue();
            Assert.assertTrue(innerBeanDef.isSynthetic());
            Assert.assertTrue(innerBeanDef.getBeanClass().equals(AspectInstanceFactory.class));
            List<PropertyValue> pvs = innerBeanDef.getPropertyValues();
            Assert.assertEquals("aspectBeanName", pvs.get(0).getName());
            Assert.assertEquals("tx", pvs.get(0).getValue());
        }
    }
}

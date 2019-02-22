package com.niocoder.test.v5;

import com.niocoder.service.v5.NioCoderService;
import com.niocoder.tx.TransactionManager;
import org.junit.Test;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * Created on 2019/2/17.
 * <p>
 * Cglib 创建代理类
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class CglibTest {

    /**
     * 所有方法都拦截!!!!!
     */
    @Test
    public void testCallBack() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(NioCoderService.class);
        enhancer.setCallback(new TransactionInterceptor());
        NioCoderService nioCoderService = (NioCoderService) enhancer.create();
        nioCoderService.placeOrder();
//        nioCoderService.toString();
    }

    /**
     *
     */
    private class TransactionInterceptor implements MethodInterceptor {
        TransactionManager tx = new TransactionManager();

        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            tx.start();
            Object result = methodProxy.invokeSuper(o, objects);
            tx.commit();
            return result;
        }
    }

    /**
     * 配置多个拦截器
     */
    @Test
    public void testFilter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(NioCoderService.class);
        enhancer.setInterceptDuringConstruction(false);

        // 两个拦截器，第二个默认不操作
        Callback[] callbacks = new Callback[]{new TransactionInterceptor(), NoOp.INSTANCE};

        Class<?>[] types = new Class[callbacks.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = callbacks[i].getClass();
        }

        enhancer.setCallbackFilter(new ProxyCallbackFilter());
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackTypes(types);

        NioCoderService nioCoderService = (NioCoderService) enhancer.create();
        nioCoderService.placeOrder();
        System.out.println(nioCoderService.toString());
    }

    private class ProxyCallbackFilter implements CallbackFilter {
        public ProxyCallbackFilter() {
        }
        @Override
        public int accept(Method method) {
            if (method.getName().startsWith("place")) {
                return 0; // 第一个拦截器
            } else {
                return 1; // 第二个拦截器
            }
        }
    }
}

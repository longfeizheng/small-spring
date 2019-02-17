package com.niocoder.aop.framework;

import com.niocoder.aop.Advice;
import com.niocoder.util.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.*;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/2/17.
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class CglibProxyFactory implements AopProxyFactory {
    // Constants for CGLIB callback array indices
    private static final int AOP_PROXY = 0;
    private static final int INVOKE_TARGET = 1;
    private static final int NO_OVERRIDE = 2;
    private static final int DISPATCH_TARGET = 3;
    private static final int DISPATCH_ADVISED = 4;
    private static final int INVOKE_EQUALS = 5;
    private static final int INVOKE_HASHCODE = 6;


    /**
     * Logger available to subclasses; static to optimize serialization
     */
    protected static final Log logger = LogFactory.getLog(CglibProxyFactory.class);


    protected final AopConfig config;

    private Object[] constructorArgs;

    private Class<?>[] constructorArgTypes;


    public CglibProxyFactory(AopConfig config) throws AopConfigException {
        Assert.notNull(config, "AdvisedSupport must not be null");
        if (config.getAdvices().size() == 0 /*&& config.getTargetSource() == AdvisedSupport.EMPTY_TARGET_SOURCE*/) {
            throw new AopConfigException("No advisors and no TargetSource specified");
        }
        this.config = config;

    }

    @Override
    public Object getProxy() {
        return getProxy(null);
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        if (logger.isDebugEnabled()) {
            logger.debug("Creating CGLIB proxy: target source is " + this.config.getTargetClass());
        }

        try {
            Class<?> rootClass = this.config.getTargetClass();

            // Configure CGLIB Enhancer...
            Enhancer enhancer = new Enhancer();
            if (classLoader != null) {
                enhancer.setClassLoader(classLoader);
            }
            enhancer.setSuperclass(rootClass);

            enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE); //"BySpringCGLIB"
            enhancer.setInterceptDuringConstruction(false);

            Callback[] callbacks = getCallbacks(rootClass);
            Class<?>[] types = new Class<?>[callbacks.length];
            for (int x = 0; x < types.length; x++) {
                types[x] = callbacks[x].getClass();
            }

            enhancer.setCallbackFilter(new ProxyCallbackFilter(this.config));
            enhancer.setCallbackTypes(types);
            enhancer.setCallbacks(callbacks);

            // Generate the proxy class and create a proxy instance.
            Object proxy = enhancer.create();

            return proxy;
        } catch (CodeGenerationException ex) {
            throw new AopConfigException("Could not generate CGLIB subclass of class [" +
                    this.config.getTargetClass() + "]: " +
                    "Common causes of this problem include using a final class or a non-visible class",
                    ex);
        } catch (IllegalArgumentException ex) {
            throw new AopConfigException("Could not generate CGLIB subclass of class [" +
                    this.config.getTargetClass() + "]: " +
                    "Common causes of this problem include using a final class or a non-visible class",
                    ex);
        } catch (Exception ex) {
            // TargetSource.getTarget() failed
            throw new AopConfigException("Unexpected AOP exception", ex);
        }
    }

    private Callback[] getCallbacks(Class<?> rootClass) throws Exception {

        Callback aopInterceptor = new DynamicAdvisedInterceptor(this.config);

        Callback[] callbacks = new Callback[]{
                aopInterceptor,  // AOP_PROXY for normal advice
                /*targetInterceptor,  // INVOKE_TARGET invoke target without considering advice, if optimized
                new SerializableNoOp(),  // NO_OVERRIDE  no override for methods mapped to this
                targetDispatcher,        //DISPATCH_TARGET
                this.advisedDispatcher,  //DISPATCH_ADVISED
                new EqualsInterceptor(this.advised),
                new HashCodeInterceptor(this.advised)*/
        };

        return callbacks;
    }


    /**
     * General purpose AOP callback. Used when the target is dynamic or when the
     * proxy is not frozen.
     */
    private static class DynamicAdvisedInterceptor implements MethodInterceptor, Serializable {

        private final AopConfig config;

        public DynamicAdvisedInterceptor(AopConfig advised) {
            this.config = advised;
        }

        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {


            Object target = this.config.getTargetObject();


            List<Advice> chain = this.config.getAdvices(method/*, targetClass*/);
            Object retVal;
            // Check whether we only have one InvokerInterceptor: that is,
            // no real advice, but just reflective invocation of the target.
            if (chain.isEmpty() && Modifier.isPublic(method.getModifiers())) {
                retVal = methodProxy.invoke(target, args);
            } else {
                List<org.aopalliance.intercept.MethodInterceptor> interceptors =
                        new ArrayList<org.aopalliance.intercept.MethodInterceptor>();

                interceptors.addAll(chain);
                // We need to create a method invocation...
                retVal = new ReflectiveMethodInvocation(target, method, args, interceptors).proceed();
            }
            //retVal = processReturnType(proxy, target, method, retVal);
            return retVal;

        }
    }

    /**
     * CallbackFilter to assign Callbacks to methods.
     */
    private static class ProxyCallbackFilter implements CallbackFilter {
        private final AopConfig config;
        public ProxyCallbackFilter(AopConfig advised) {
            this.config = advised;

        }

        public int accept(Method method) {
            // 注意，这里做了简化
            return AOP_PROXY;

        }

    }
}

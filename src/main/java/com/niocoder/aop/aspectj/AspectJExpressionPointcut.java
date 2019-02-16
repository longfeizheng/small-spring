package com.niocoder.aop.aspectj;

import com.niocoder.aop.MethodMatcher;
import com.niocoder.aop.Pointcut;
import com.niocoder.util.ClassUtils;
import com.niocoder.util.StringUtils;
import org.aspectj.weaver.reflect.ReflectionWorld;
import org.aspectj.weaver.tools.*;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 2019/2/16.
 * 实现 Pointcut 和 MethodMatcher 判断表达式是否匹配（需要引入aspectj 依赖）
 *
 * @author zlf
 * @email i@merryyou.cn
 * @since 1.0
 */
public class AspectJExpressionPointcut implements Pointcut, MethodMatcher {
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    /**
     * 条件表达式 即 expression="execution(* com.niocoder.service.v5.*.placeOrder(..))"
     */
    private String expression;

    private PointcutExpression pointcutExpression;

    private ClassLoader pointcutClassLoader;

    public AspectJExpressionPointcut() {

    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    @Override
    public String getExpression() {
        return this.expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public boolean matches(Method method/*, Class<?> targetClass*/) {

        // 判断是否设置条件表达式
        checkReadyToMatch();

        // 根据传入的method 返回shadowatch
        ShadowMatch shadowMatch = getShadowMatch(method);

        // 判断是否匹配
        if (shadowMatch.alwaysMatches()) {
            return true;
        }

        return false;
    }

    private ShadowMatch getShadowMatch(Method method) {

        ShadowMatch shadowMatch = null;
        try {
            shadowMatch = this.pointcutExpression.matchesMethodExecution(method);
        } catch (ReflectionWorld.ReflectionWorldException ex) {

            throw new RuntimeException("not implemented yet");
        }
        return shadowMatch;
    }

    private void checkReadyToMatch() {
        if (getExpression() == null) {
            throw new IllegalStateException("Must set property 'expression' before attempting to match");
        }
        if (this.pointcutExpression == null) {
            this.pointcutClassLoader = ClassUtils.getDefaultClassLoader();
            this.pointcutExpression = buildPointcutExpression(this.pointcutClassLoader);
        }
    }

    private PointcutExpression buildPointcutExpression(ClassLoader classLoader) {

        PointcutParser parser = PointcutParser
                .getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(
                        SUPPORTED_PRIMITIVES, classLoader);

        return parser.parsePointcutExpression(replaceBooleanOperators(getExpression()),
                null, new PointcutParameter[0]);
    }


    private String replaceBooleanOperators(String pcExpr) {
        String result = StringUtils.replace(pcExpr, " and ", " && ");
        result = StringUtils.replace(result, " or ", " || ");
        result = StringUtils.replace(result, " not ", " ! ");
        return result;
    }
}

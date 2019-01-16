package com.niocoder.context.support;

import com.niocoder.core.io.ClassPathResource;
import com.niocoder.core.io.Resource;

/**
 * 从classpath下读取bean.xml
 *
 * @author zhenglongfei
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {


    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String configFile) {
        return new ClassPathResource(configFile);
    }
}

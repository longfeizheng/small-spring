package com.niocoder.context.support;

import com.niocoder.core.io.FileSystemResource;
import com.niocoder.core.io.Resource;

/**
 * 从一个目录下读取bean.xml
 *
 * @author zhenglongfei
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String configFile) {
        return new FileSystemResource(configFile);
    }
}

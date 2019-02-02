package com.niocoder.test.v4;

import com.niocoder.beans.factory.support.DefaultBeanFactory;
import com.niocoder.beans.factory.xml.XmlBeanDefinitionReader;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.stereotype.Component;
import org.junit.Test;

/**
 * 测试读取xml中 component-scan标签的读取
 */
public class XmlBeanDefinitionReaderTest {

    @Test
    public void testParseScannerBean() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(new ClassPathResource("bean-v4.xml"));

        String annotation = Component.class.getName();

        ClassPathBeanDefinitionScannerTest.testAnnotationTest(factory, annotation);
    }
}

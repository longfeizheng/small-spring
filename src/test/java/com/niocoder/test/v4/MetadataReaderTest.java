package com.niocoder.test.v4;

import com.niocoder.core.annotation.AnnotationAttributes;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.core.type.AnnotationMetadata;
import com.niocoder.core.type.classreading.MetadataReader;
import com.niocoder.core.type.classreading.SimpleMetadataReader;
import com.niocoder.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

/**
 * 测试封装后的MetadataReader
 */
public class MetadataReaderTest {

    @Test
    public void testGetMetadata() throws Exception{
        ClassPathResource resource = new ClassPathResource("com/niocoder/service/v4/NioCoderService.class");

        MetadataReader reader = new SimpleMetadataReader(resource);
        AnnotationMetadata amd = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();

        Assert.assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
        Assert.assertEquals("nioCoder",attributes.get("value"));

        Assert.assertFalse(amd.isAbstract());
        Assert.assertFalse(amd.isInterface());
        Assert.assertFalse(amd.isFinal());
    }
}

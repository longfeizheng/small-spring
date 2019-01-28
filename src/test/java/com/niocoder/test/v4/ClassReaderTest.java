package com.niocoder.test.v4;

import com.niocoder.core.annotation.AnnotationAttributes;
import com.niocoder.core.io.ClassPathResource;
import com.niocoder.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.niocoder.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;

/**
 *
 */
public class ClassReaderTest {

    @Test
    public void testGetClassMetaData() throws Exception {
        ClassPathResource resource = new ClassPathResource("com/niocoder/service/v4/NioCoderService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertEquals("com.niocoder.service.v4.NioCoderService", visitor.getClassName());
        Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
        Assert.assertEquals(0, visitor.getInterfaces().length);
    }

    @Test
    public void testGetAnnotation() throws Exception {
        ClassPathResource resource = new ClassPathResource("com/niocoder/service/v4/NioCoderService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();

        reader.accept(visitor,ClassReader.SKIP_DEBUG);

        String annotation = "com.niocoder.stereotype.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));

        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);

        Assert.assertEquals("nioCoder",attributes.get("value"));
    }
}

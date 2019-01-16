package com.niocoder.test.v1;

import com.niocoder.core.io.ClassPathResource;
import com.niocoder.core.io.FileSystemResource;
import com.niocoder.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

/**
 * Resource测试类
 */
public class ResourceTest {

    @Test
    public void testClassPathResource() throws Exception {
        Resource resource = new ClassPathResource("bean-v1.xml");
        Assert.assertNotNull(resource.getInputStream());
    }

    @Test
    public void testFileSystemResource() throws Exception {
        Resource resource = new FileSystemResource("src/test/resources/bean-v1.xml");
        Assert.assertNotNull(resource.getInputStream());
    }
}

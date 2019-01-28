package com.niocoder.test.v4;

import com.niocoder.core.io.Resource;
import com.niocoder.core.io.support.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class PackageResourcesLoaderTest {

    @Test
    public void testGetResource() throws Exception {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resource = loader.getResource("com.niocoder.dao.v4");
        Assert.assertEquals(2, resource.length);
    }
}

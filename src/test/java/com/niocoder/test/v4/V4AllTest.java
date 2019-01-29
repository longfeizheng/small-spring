package com.niocoder.test.v4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PackageResourcesLoaderTest.class,
        ClassReaderTest.class,
        MetadataReaderTest.class})
public class V4AllTest {
}

package com.niocoder.test;

import com.niocoder.test.v1.V1AllTest;
import com.niocoder.test.v2.V2AllTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        V1AllTest.class,
        V2AllTest.class})
public class AllTest {
}

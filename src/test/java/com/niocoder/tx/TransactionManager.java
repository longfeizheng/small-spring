package com.niocoder.tx;

import com.niocoder.util.MessageTracker;
import org.junit.Before;

/**
 * 用于测试AOP顺序
 */
public class TransactionManager {
    @Before
    public void setUp() {
        MessageTracker.clearMsgs();
    }

    public void start() {
        System.out.println("start tx");
        MessageTracker.addMsg("start tx");
    }

    public void commit() {
        System.out.println("commit tx");
        MessageTracker.addMsg("commit tx");
    }

    public void rollback() {
        System.out.println("rollback tx");
        MessageTracker.addMsg("rollback tx");
    }
}

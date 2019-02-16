package com.niocoder.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 记录msg
 */
public class MessageTracker {
    private static List<String> MESSAGES = new ArrayList<>();

    public static void addMsg(String msg) {
        MESSAGES.add(msg);
    }

    public static void clearMsgs() {
        MESSAGES.clear();
    }

    public static List<String> getMsgs() {
        return MESSAGES;
    }

}

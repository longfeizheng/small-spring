package com.niocoder.service.v6;

import com.niocoder.stereotype.Component;
import com.niocoder.util.MessageTracker;

/**
 *
 */
@Component(value = "nioCoder")
public class NioCoderService implements INioCoderService {

    public NioCoderService() {

    }


    public void placeOrder() {
        System.out.println("place order");
        MessageTracker.addMsg("place order");
    }

    public void placeOrderV2() {
        System.out.println("no interception");
    }
}

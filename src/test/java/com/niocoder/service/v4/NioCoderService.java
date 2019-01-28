package com.niocoder.service.v4;

import com.niocoder.beans.factory.Autowired;
import com.niocoder.dao.v4.AccountDao;
import com.niocoder.dao.v4.ItemDao;
import com.niocoder.stereotype.Component;

/**
 * 测试注解
 */
@Component(value = "nioCoder")
public class NioCoderService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }
}

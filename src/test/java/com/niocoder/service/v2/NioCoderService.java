package com.niocoder.service.v2;

import com.niocoder.dao.v2.AccountDao;
import com.niocoder.dao.v2.ItemDao;

public class NioCoderService {

    private AccountDao accountDao;

    private ItemDao itemDao;

    private String url;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

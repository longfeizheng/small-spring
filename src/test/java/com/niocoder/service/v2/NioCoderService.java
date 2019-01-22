package com.niocoder.service.v2;

import com.niocoder.dao.v2.AccountDao;
import com.niocoder.dao.v2.ItemDao;

import java.util.Date;

public class NioCoderService {

    private AccountDao accountDao;

    private ItemDao itemDao;

    private String url;

    private Date birthday; // date类型 需要特殊处理

    private Boolean flag; // boolean 类型，特殊处理

    private Integer version; // Integer 类型 特殊处理

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public String getUrl() {
        return url;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}

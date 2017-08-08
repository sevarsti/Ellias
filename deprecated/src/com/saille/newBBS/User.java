package com.saille.newBBS;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.ClassDescription;
import com.sinitek.dao.domain.PropertyDescription;

import java.util.Date;

@ClassDescription(table = "USER")
public class User extends BaseEntity {
    private String userId;
    private String lowerId;
    private Date firstLogin;
    private String lastHost;
    private int numLogins;
    private int numPosts;
    private String passwd;
    private int userdefine;
    private Date createTime;
    private Date removeTime;

    @PropertyDescription(persistant = true, sqlType = 12)
    public String getUserId() {
        return this.userId;
    }

    @PropertyDescription(persistant = true)
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @PropertyDescription(persistant = true, sqlType = 93)
    public Date getFirstLogin() {
        return this.firstLogin;
    }

    @PropertyDescription(persistant = true)
    public void setFirstLogin(Date firstLogin) {
        this.firstLogin = firstLogin;
    }

    @PropertyDescription(persistant = true, sqlType = 12)
    public String getLastHost() {
        return this.lastHost;
    }

    @PropertyDescription(persistant = true)
    public void setLastHost(String lastHost) {
        this.lastHost = lastHost;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getNumLogins() {
        return this.numLogins;
    }

    @PropertyDescription(persistant = true)
    public void setNumLogins(int numLogins) {
        this.numLogins = numLogins;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getNumPosts() {
        return this.numPosts;
    }

    @PropertyDescription(persistant = true)
    public void setNumPosts(int numPosts) {
        this.numPosts = numPosts;
    }

    @PropertyDescription(persistant = true, sqlType = 12)
    public String getPasswd() {
        return this.passwd;
    }

    @PropertyDescription(persistant = true)
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getUserdefine() {
        return this.userdefine;
    }

    @PropertyDescription(persistant = true)
    public void setUserdefine(int userdefine) {
        this.userdefine = userdefine;
    }

    @PropertyDescription(persistant = true, sqlType = 93)
    public Date getCreateTime() {
        return this.createTime;
    }

    @PropertyDescription(persistant = true)
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @PropertyDescription(persistant = true, sqlType = 93)
    public Date getRemoveTime() {
        return this.removeTime;
    }

    @PropertyDescription(persistant = true)
    public void setRemoveTime(Date removeTime) {
        this.removeTime = removeTime;
    }

    @PropertyDescription(persistant = true, sqlType = 12)
    public String getLowerId() {
        return this.lowerId;
    }

    @PropertyDescription(persistant = true)
    public void setLowerId(String lowerId) {
        this.lowerId = lowerId;
    }
}
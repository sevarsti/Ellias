package com.saille.sys.form;

import com.saille.sys.Right;
import org.apache.struts.action.ActionForm;
import java.util.List;

public class RightForm extends ActionForm {
    private List<Right> rights;
    private String msg;
    private int id;
    private int resourceId;
    private int orgId;
    private int orgType;
    private int auth;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Right> getRights() {
        return this.rights;
    }

    public void setRights(List<Right> list) {
        this.rights = list;
    }

    public int getResourceId() {
        return this.resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getOrgId() {
        return this.orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getOrgType() {
        return this.orgType;
    }

    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }

    public int getAuth() {
        return this.auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }
}

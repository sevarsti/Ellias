package com.saille.msn.form;

import com.saille.msn.MsnMain;
import org.apache.struts.action.ActionForm;

public class MsnForm extends ActionForm {
    public static final int CONTACT_LIST_ORDER_DISPLAYNAME = 1;
    public static final int CONTACT_LIST_ORDER_EMAIL = 2;
    public static final int CONTACT_LIST_ORDER_STATUS = 3;
    private int contactListOrder = 0;
    private String username;
    private String password;
    private MsnMain main;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MsnMain getMain() {
        return this.main;
    }

    public void setMain(MsnMain main) {
        this.main = main;
    }

    public int getContactListOrder() {
        return this.contactListOrder;
    }

    public void setContactListOrder(int contactListOrder) {
        this.contactListOrder = contactListOrder;
    }
}
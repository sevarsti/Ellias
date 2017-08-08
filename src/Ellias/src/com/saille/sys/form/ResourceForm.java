package com.saille.sys.form;

import com.saille.sys.Resource;
import org.apache.struts.action.ActionForm;
import java.util.List;

public class ResourceForm extends ActionForm {
    private List<Resource> resources;
    private String msg;
    private int id;
    private String name;
    private int parentId;
    private String url;
    private String methodname;
    private String memo;

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

    public List<Resource> getResources() {
        return this.resources;
    }

    public void setResources(List<Resource> list) {
        this.resources = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return this.parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}

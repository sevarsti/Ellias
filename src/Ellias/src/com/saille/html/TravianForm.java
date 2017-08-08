package com.saille.html;

import java.util.List;

public class TravianForm {
    private String url;
    private String formName;
    private String[] param;
    private String[] paramValue;
    private List<String> links;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormName() {
        return this.formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String[] getParam() {
        return this.param;
    }

    public void setParam(String[] param) {
        this.param = param;
    }

    public void setParam(List<String> param) {
        this.param = new String[param.size()];
        param.toArray(this.param);
    }

    public String[] getParamValue() {
        return this.paramValue;
    }

    public void setParamValue(String[] paramValue) {
        this.paramValue = paramValue;
    }

    public void setParamValue(List<String> paramValue) {
        this.paramValue = new String[paramValue.size()];
        paramValue.toArray(this.paramValue);
    }

    public List<String> getLinks() {
        return this.links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
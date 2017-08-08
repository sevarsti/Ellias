package com.saille.sys;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.PropertyDescription;
import com.sinitek.dao.domain.ClassDescription;
import java.sql.Types;
@ClassDescription(table = "RESOURCE")
public class Resource extends BaseEntity {
    public final static int ROOT_ID = 1;
    public final static int MENU_ROOT_ID = 2;
    public final static int OTHER_ROOT_ID = 3;
    private String name;
    private int parentId;
    private String url;
    private String methodname;
    private String memo;
    private int level;
    private String rights;

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getName() {
        return this.name;
    }

    @PropertyDescription(persistant = true)
    public void setName(String name) {
        this.name = name;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getParentId() {
        return this.parentId;
    }

    @PropertyDescription(persistant = true)
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getUrl() {
        return url;
    }

    @PropertyDescription(persistant = true)
    public void setUrl(String url) {
        this.url = url;
    }

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getMethodname() {
        return methodname;
    }

    @PropertyDescription(persistant = true)
    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getMemo() {
        return this.memo;
    }

    @PropertyDescription(persistant = true)
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}

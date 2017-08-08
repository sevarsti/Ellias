package com.saille.core;

import com.sinitek.dao.domain.PropertyDescription;

import java.util.Date;
import java.sql.Types;

public class BaseEntity {
    protected int id;
    protected int removeTag;
    protected Date updateTime;
    protected Date createTime;

    @PropertyDescription(primaryKey = true, persistant = true)
    public int getId() {
        return this.id;
    }

    @PropertyDescription(primaryKey = true, persistant = true)
    public void setId(int id) {
        this.id = id;
    }

    @PropertyDescription(persistant = true, sqlType = Types.TIMESTAMP)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    @PropertyDescription(persistant = true)
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @PropertyDescription(persistant = true, sqlType = Types.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    @PropertyDescription(persistant = true)
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getRemoveTag() {
        return removeTag;
    }

    @PropertyDescription(persistant = true)
    public void setRemoveTag(int removeTag) {
        this.removeTag = removeTag;
    }
}
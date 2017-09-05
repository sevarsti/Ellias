package com.saille.sys;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.PropertyDescription;
import com.sinitek.dao.domain.ClassDescription;
import java.sql.Types;
@ClassDescription(table = "SYS_POSITION")
public class Position extends BaseEntity {
    private String name;
    private int parentId;
    private String memo;
    private int level;

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
}

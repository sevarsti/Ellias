package com.saille.sys;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.PropertyDescription;
import com.sinitek.dao.domain.ClassDescription;
import java.sql.Types;
@ClassDescription(table = "RIGHT")
public class Right extends BaseEntity {
    public final static int ORGTYPE_POSITION = 1;
    public final static int ORGTYPE_EMPLOYEE = 2;
    
    private int resourceId;
    private int orgId;
    private int orgType;
    private int auth;

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getResourceId() {
        return this.resourceId;
    }

    @PropertyDescription(persistant = true)
    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getOrgId() {
        return this.orgId;
    }

    @PropertyDescription(persistant = true)
    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getOrgType() {
        return this.orgType;
    }

    @PropertyDescription(persistant = true)
    public void setOrgType(int orgType) {
        this.orgType = orgType;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getAuth() {
        return this.auth;
    }

    @PropertyDescription(persistant = true)
    public void setAuth(int auth) {
        this.auth = auth;
    }
}

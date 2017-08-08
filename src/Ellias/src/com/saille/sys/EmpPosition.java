package com.saille.sys;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.PropertyDescription;
import com.sinitek.dao.domain.ClassDescription;
import java.sql.Types;
@ClassDescription(table = "EMPPOSITION")
public class EmpPosition extends BaseEntity {
    private int empId;
    private int positionId;

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getEmpId() {
        return this.empId;
    }

    @PropertyDescription(persistant = true)
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getPositionId() {
        return this.positionId;
    }

    @PropertyDescription(persistant = true)
    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}

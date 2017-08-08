package com.saille.sys.form;

import com.saille.sys.EmpPosition;
import org.apache.struts.action.ActionForm;
import java.util.List;

public class EmpPositionForm extends ActionForm {
    private List<EmpPosition> empPositions;
    private String msg;
    private int id;
    private int empId;
    private int positionId;

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

    public List<EmpPosition> getEmpPositions() {
        return this.empPositions;
    }

    public void setEmpPositions(List<EmpPosition> list) {
        this.empPositions = list;
    }

    public int getEmpId() {
        return this.empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getPositionId() {
        return this.positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}

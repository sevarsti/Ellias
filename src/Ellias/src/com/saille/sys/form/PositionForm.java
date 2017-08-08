package com.saille.sys.form;

import com.saille.sys.Position;
import org.apache.struts.action.ActionForm;
import java.util.List;

public class PositionForm extends ActionForm {
    private List<Position> positions;
    private String msg;
    private int id;
    private String name;
    private int parentId;
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

    public List<Position> getPositions() {
        return this.positions;
    }

    public void setPositions(List<Position> list) {
        this.positions = list;
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

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}

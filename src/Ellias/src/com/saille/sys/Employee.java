package com.saille.sys;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.PropertyDescription;
import com.sinitek.dao.domain.ClassDescription;
import java.sql.Types;
@ClassDescription(table = "EMPLOYEE")
public class Employee extends BaseEntity {
    private String name;
    private String loginname;
    private String pwd;
//    private int positionId;
    private int gender;
    private int birth;
    private String mobile;
    private String email;
    private String memo;
    private String positionNames;

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getName() {
        return this.name;
    }

    @PropertyDescription(persistant = true)
    public void setName(String name) {
        this.name = name;
    }

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getLoginname() {
        return loginname;
    }

    @PropertyDescription(persistant = true)
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getPwd() {
        return pwd;
    }

    @PropertyDescription(persistant = true)
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

//    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
//    public int getPositionId() {
//        return positionId;
//    }
//
//    @PropertyDescription(persistant = true)
//    public void setPositionId(int positionId) {
//        this.positionId = positionId;
//    }

    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getGender() {
        return this.gender;
    }

    @PropertyDescription(persistant = true)
    public void setGender(int gender) {
        this.gender = gender;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getBirth() {
        return this.birth;
    }

    @PropertyDescription(persistant = true)
    public void setBirth(int birth) {
        this.birth = birth;
    }
    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getMobile() {
        return this.mobile;
    }

    @PropertyDescription(persistant = true)
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getEmail() {
        return this.email;
    }

    @PropertyDescription(persistant = true)
    public void setEmail(String email) {
        this.email = email;
    }
    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getMemo() {
        return this.memo;
    }

    @PropertyDescription(persistant = true)
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPositionNames() {
        return positionNames;
    }

    public void setPositionNames(String positionNames) {
        this.positionNames = positionNames;
    }
}

package com.saille.sys.form;

import com.saille.sys.Employee;
import org.apache.struts.action.ActionForm;
import java.util.List;

public class EmployeeForm extends ActionForm {
    private List<Employee> employees;
    private Employee employee;
    private String msg;
    private int id;
    private String name;
    private String pwd;
    private String loginname;
//    private int positionId;
    private int gender;
    private int birth;
    private String mobile;
    private String email;
    private String memo;
    private String positionIds;

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

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> list) {
        this.employees = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

//    public int getPositionId() {
//        return positionId;
//    }
//
//    public void setPositionId(int positionId) {
//        this.positionId = positionId;
//    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getBirth() {
        return this.birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String positionIds) {
        this.positionIds = positionIds;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

package com.ysgh.btr.test1.pojo;

/**
 * UserRole领域模型实体类
 */
public class UserRole {
    private int id;
    private String userid;
    private String roleid;

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", roleid='" + roleid + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
}

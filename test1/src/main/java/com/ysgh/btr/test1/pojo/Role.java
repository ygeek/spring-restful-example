package com.ysgh.btr.test1.pojo;


import java.sql.Timestamp;

/**
 * Role领域模型实体类
 */
public class Role {
    private int roleid;
    private String rolename;
    private Timestamp version;


    @Override
    public String toString() {
        return "Role{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                ", version=" + version +
                '}';
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Timestamp getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }
}

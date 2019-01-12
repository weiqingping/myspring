package com.my.test.dao.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer userId;

    /**
     * VARCHAR(45)<br>
     * 
     */
    private String userName;

    /**
     * INTEGER(10) 必填<br>
     * 获得 
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * INTEGER(10) 必填<br>
     * 设置 
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * VARCHAR(45)<br>
     * 获得 
     */
    public String getUserName() {
        return userName;
    }

    /**
     * VARCHAR(45)<br>
     * 设置 
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        UserInfo other = (UserInfo) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        return result;
    }
}
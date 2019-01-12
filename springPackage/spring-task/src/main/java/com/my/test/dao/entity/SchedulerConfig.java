package com.my.test.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class SchedulerConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    public SchedulerConfig(Integer id, String name, String jobClass, String jobBeanName, String methodName,
			String cronExpression, Integer concurrent, String enableIp, Long delayTime, Integer flag, Date effectTime,
			Date expireTime, byte[] arguments) {
		super();
		this.id = id;
		this.name = name;
		this.jobClass = jobClass;
		this.jobBeanName = jobBeanName;
		this.methodName = methodName;
		this.cronExpression = cronExpression;
		this.concurrent = concurrent;
		this.enableIp = enableIp;
		this.delayTime = delayTime;
		this.flag = flag;
		this.effectTime = effectTime;
		this.expireTime = expireTime;
		this.arguments = arguments;
	}

	/**
     * INTEGER(10) 必填<br>
     * 
     */
    private Integer id;

    /**
     * VARCHAR(45) 必填<br>
     * 
     */
    private String name;

    /**
     * VARCHAR(45)<br>
     * 
     */
    private String jobClass;

    /**
     * VARCHAR(45) 必填<br>
     * 
     */
    private String jobBeanName;

    /**
     * VARCHAR(45) 必填<br>
     * 
     */
    private String methodName;

    /**
     * VARCHAR(45) 必填<br>
     * 
     */
    private String cronExpression;

    /**
     * INTEGER(10) 默认值[0]<br>
     * 
     */
    private Integer concurrent;

    /**
     * VARCHAR(45)<br>
     * 
     */
    private String enableIp;

    /**
     * BIGINT(19)<br>
     * 
     */
    private Long delayTime;

    /**
     * INTEGER(10) 默认值[1]<br>
     * 
     */
    private Integer flag;

    /**
     * TIMESTAMP(19)<br>
     * 
     */
    private Date effectTime;

    /**
     * TIMESTAMP(19)<br>
     * 
     */
    private Date expireTime;

    /**
     * LONGVARBINARY(65535)<br>
     * 
     */
    private byte[] arguments;

    /**
     * INTEGER(10) 必填<br>
     * 获得 
     */
    public Integer getId() {
        return id;
    }

    /**
     * INTEGER(10) 必填<br>
     * 设置 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * VARCHAR(45) 必填<br>
     * 获得 
     */
    public String getName() {
        return name;
    }

    /**
     * VARCHAR(45) 必填<br>
     * 设置 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * VARCHAR(45)<br>
     * 获得 
     */
    public String getJobClass() {
        return jobClass;
    }

    /**
     * VARCHAR(45)<br>
     * 设置 
     */
    public void setJobClass(String jobClass) {
        this.jobClass = jobClass == null ? null : jobClass.trim();
    }

    /**
     * VARCHAR(45) 必填<br>
     * 获得 
     */
    public String getJobBeanName() {
        return jobBeanName;
    }

    /**
     * VARCHAR(45) 必填<br>
     * 设置 
     */
    public void setJobBeanName(String jobBeanName) {
        this.jobBeanName = jobBeanName == null ? null : jobBeanName.trim();
    }

    /**
     * VARCHAR(45) 必填<br>
     * 获得 
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * VARCHAR(45) 必填<br>
     * 设置 
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * VARCHAR(45) 必填<br>
     * 获得 
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * VARCHAR(45) 必填<br>
     * 设置 
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 获得 
     */
    public Integer getConcurrent() {
        return concurrent;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 设置 
     */
    public void setConcurrent(Integer concurrent) {
        this.concurrent = concurrent;
    }

    /**
     * VARCHAR(45)<br>
     * 获得 
     */
    public String getEnableIp() {
        return enableIp;
    }

    /**
     * VARCHAR(45)<br>
     * 设置 
     */
    public void setEnableIp(String enableIp) {
        this.enableIp = enableIp == null ? null : enableIp.trim();
    }

    /**
     * BIGINT(19)<br>
     * 获得 
     */
    public Long getDelayTime() {
        return delayTime;
    }

    /**
     * BIGINT(19)<br>
     * 设置 
     */
    public void setDelayTime(Long delayTime) {
        this.delayTime = delayTime;
    }

    /**
     * INTEGER(10) 默认值[1]<br>
     * 获得 
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * INTEGER(10) 默认值[1]<br>
     * 设置 
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * TIMESTAMP(19)<br>
     * 获得 
     */
    public Date getEffectTime() {
        return effectTime;
    }

    /**
     * TIMESTAMP(19)<br>
     * 设置 
     */
    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    /**
     * TIMESTAMP(19)<br>
     * 获得 
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * TIMESTAMP(19)<br>
     * 设置 
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * LONGVARBINARY(65535)<br>
     * 获得 
     */
    public byte[] getArguments() {
        return arguments;
    }

    /**
     * LONGVARBINARY(65535)<br>
     * 设置 
     */
    public void setArguments(byte[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", jobClass=").append(jobClass);
        sb.append(", jobBeanName=").append(jobBeanName);
        sb.append(", methodName=").append(methodName);
        sb.append(", cronExpression=").append(cronExpression);
        sb.append(", concurrent=").append(concurrent);
        sb.append(", enableIp=").append(enableIp);
        sb.append(", delayTime=").append(delayTime);
        sb.append(", flag=").append(flag);
        sb.append(", effectTime=").append(effectTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", arguments=").append(arguments);
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
        SchedulerConfig other = (SchedulerConfig) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getJobClass() == null ? other.getJobClass() == null : this.getJobClass().equals(other.getJobClass()))
            && (this.getJobBeanName() == null ? other.getJobBeanName() == null : this.getJobBeanName().equals(other.getJobBeanName()))
            && (this.getMethodName() == null ? other.getMethodName() == null : this.getMethodName().equals(other.getMethodName()))
            && (this.getCronExpression() == null ? other.getCronExpression() == null : this.getCronExpression().equals(other.getCronExpression()))
            && (this.getConcurrent() == null ? other.getConcurrent() == null : this.getConcurrent().equals(other.getConcurrent()))
            && (this.getEnableIp() == null ? other.getEnableIp() == null : this.getEnableIp().equals(other.getEnableIp()))
            && (this.getDelayTime() == null ? other.getDelayTime() == null : this.getDelayTime().equals(other.getDelayTime()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getEffectTime() == null ? other.getEffectTime() == null : this.getEffectTime().equals(other.getEffectTime()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getArguments() == null ? other.getArguments() == null : this.getArguments().equals(other.getArguments()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getJobClass() == null) ? 0 : getJobClass().hashCode());
        result = prime * result + ((getJobBeanName() == null) ? 0 : getJobBeanName().hashCode());
        result = prime * result + ((getMethodName() == null) ? 0 : getMethodName().hashCode());
        result = prime * result + ((getCronExpression() == null) ? 0 : getCronExpression().hashCode());
        result = prime * result + ((getConcurrent() == null) ? 0 : getConcurrent().hashCode());
        result = prime * result + ((getEnableIp() == null) ? 0 : getEnableIp().hashCode());
        result = prime * result + ((getDelayTime() == null) ? 0 : getDelayTime().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getEffectTime() == null) ? 0 : getEffectTime().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getArguments() == null) ? 0 : getArguments().hashCode());
        return result;
    }
}
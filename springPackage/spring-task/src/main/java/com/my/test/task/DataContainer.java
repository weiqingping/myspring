package com.my.test.task;

import java.util.Date;

public class DataContainer {
	private Date effectDate;
	private Date expireDate;
	private String ip;
	
	
	public DataContainer(Date effectDate, Date expireDate, String ip) {
		this.effectDate = effectDate;
		this.expireDate = expireDate;
		this.ip = ip;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", effectDate=").append(effectDate.toString());
        sb.append(", expireDate=").append(expireDate.toString());
        sb.append(", ip=").append(ip);
        sb.append("]");
        return sb.toString();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getEfffectDate() {
		return effectDate;
	}

	public void setEfffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	

}

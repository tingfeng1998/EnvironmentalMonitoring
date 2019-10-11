package bean;

import java.io.Serializable;
import java.sql.Timestamp;
/*
 * 环境对象
 */
public class Environment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 环境种类名称
	private String name;
	// 发送端id
	private String sendId;
	// 树莓派系统id
	private String smId;
	// 实验箱区域模块id(1-8)
	private String qmId;
	// 模块上传感器地址
	private String address;
	// 传感器个数
	private int count;
	// 指令标号(3表示需要接受数据 16表示需要发送数据)
	private String ordernumber;
	// 状态 默认为1表示成功
	private int status;
	// 环境值
	private float data;
	// 采集时间
	private Timestamp gather_date;
	

	public Environment() {

	}

	public Environment(String name, String sendId, String smId, String qmId, String address, int count,
			String ordernumber, int status, float data, Timestamp gather_date) {
		super();
		this.name = name;
		this.sendId = sendId;
		this.smId = smId;
		this.qmId = qmId;
		this.address = address;
		this.count = count;
		this.ordernumber = ordernumber;
		this.status = status;
		this.data = data;
		this.gather_date = gather_date;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getSmId() {
		return smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	public String getQmId() {
		return qmId;
	}

	public void setQmId(String qmId) {
		this.qmId = qmId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getData() {
		return data;
	}

	public void setData(float data) {
		this.data = data;
	}

	public Timestamp getGather_date() {
		return gather_date;
	}

	public void setGather_date(Timestamp gather_date) {
		this.gather_date = gather_date;
	}

	@Override
	public String toString() {
		return "Environment [name=" + name + ", sendId=" + sendId + ", smId=" + smId + ", qmId=" + qmId + ", address="
				+ address + ", count=" + count + ", ordernumber=" + ordernumber + ", status=" + status + ", data="
				+ data + ", gather_date=" + gather_date + "]";
	}

}
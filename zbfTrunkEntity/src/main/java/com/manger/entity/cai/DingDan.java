package com.manger.entity.cai;

import java.util.Date;

import com.manger.core.entity.IdEntity;

public class DingDan extends IdEntity{
	
	private static final long serialVersionUID = 4223903379980544732L;
	private String buyer_name;
	private String openId;
	private String goods_count;
	private String total_money;
	private int order_status;
	private int pay_status;
	private Date createtime;
	private Date updatetime;
	public DingDan() {
		super();
	}
	public String getBuyer_name() {
		return buyer_name;
	}
	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getGoods_count() {
		return goods_count;
	}
	public void setGoods_count(String goods_count) {
		this.goods_count = goods_count;
	}
	public String getTotal_money() {
		return total_money;
	}
	public void setTotal_money(String total_money) {
		this.total_money = total_money;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public int getPay_status() {
		return pay_status;
	}
	public void setPay_status(int pay_status) {
		this.pay_status = pay_status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	

}

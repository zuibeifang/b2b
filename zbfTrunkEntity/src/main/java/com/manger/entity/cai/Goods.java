package com.manger.entity.cai;

import java.util.Date;
import com.manger.core.entity.IdEntity;

public class Goods extends IdEntity {
		
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public int getUp_down() {
		return up_down;
	}
	public void setUp_down(int up_down) {
		this.up_down = up_down;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
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
	private static final long serialVersionUID = -1340140239611602918L;
	private String name;
	private Long product_id;
	private int up_down;
	private double price;
	private String descript;
	private String imgUrl;
	private String stock;
	private Date createtime;
	private Date updatetime;
}

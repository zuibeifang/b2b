package com.manger.entity.authorInfo;

import java.util.Date;
import java.util.List;

import com.manger.core.entity.IdEntity;

public class Menu extends IdEntity{
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private String menuName;
  private String url;
  private String imagePath;
  private String code;
  private String parentCode;
  private String leval;
  private List<Menu> listm;
  private Date createTime;

  
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public String getImagePath() {
	return imagePath;
}
public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
}
public List<Menu> getListm() {
	return listm;
}
public void setListm(List<Menu> listm) {
	this.listm = listm;
}
public String getMenuName() {
	return menuName;
}
public void setMenuName(String menuName) {
	this.menuName = menuName;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getParentCode() {
	return parentCode;
}
public void setParentCode(String parentCode) {
	this.parentCode = parentCode;
}
public String getLeval() {
	return leval;
}
public void setLeval(String leval) {
	this.leval = leval;
}
  
}

package com.manger.entity.user;

import java.util.Date;
import java.util.List;

import com.manger.core.entity.IdEntity;

public class User extends IdEntity{
   
   /**
	 * 
	 */
   private static final long serialVersionUID = 4527120094775472728L;
   private String userName;//用户名
   private String loginName;//登录名
   private String passWord;//密码
   private String salt;//盐值
   private String buMen;//部门
   private String tel;//电话
   private long roleId;//角色Id
   private String imageUrl;
   private Date createTime;//创建时间

   
   private List<String> userAuthrity;//权限列表
 
   
public List<String> getUserAuthrity() {
	return userAuthrity;
}
public void setUserAuthrity(List<String> userAuthrity) {
	this.userAuthrity = userAuthrity;
}
public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public long getRoleId() {
	return roleId;
}
public void setRoleId(long roleId) {
	this.roleId = roleId;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getLoginName() {
	return loginName;
}
public void setLoginName(String loginName) {
	this.loginName = loginName;
}
public String getPassWord() {
	return passWord;
}
public void setPassWord(String passWord) {
	this.passWord = passWord;
}
public String getSalt() {
	return salt;
}
public void setSalt(String salt) {
	this.salt = salt;
}
public String getBuMen() {
	return buMen;
}
public void setBuMen(String buMen) {
	this.buMen = buMen;
}
   
}

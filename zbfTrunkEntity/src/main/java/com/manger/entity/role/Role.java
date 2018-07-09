package com.manger.entity.role;

import com.manger.core.entity.IdEntity;

public class Role extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//角色名称
	private String name;
	//角色描述
	private String roleMiaoShu;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleMiaoShu() {
		return roleMiaoShu;
	}
	public void setRoleMiaoShu(String roleMiaoShu) {
		this.roleMiaoShu = roleMiaoShu;
	}
	
	
	
}

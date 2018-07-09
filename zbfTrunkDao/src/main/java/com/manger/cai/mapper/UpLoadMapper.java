package com.manger.cai.mapper;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.manger.core.dao.BaseMapper;
import com.manger.entity.cai.BaseImage;
import com.manger.entity.cai.Goods;
public interface UpLoadMapper extends BaseMapper<BaseImage>{

	public void addLogoImage(BaseImage bi);
	
	public String selectLogo(Long logoMsgId);
	
	public void insertGoods(Goods goods);
	
	public void updateImage(Map<String, Object> map);
	
	public void updateGoods(Map<String, Object> map2);

}

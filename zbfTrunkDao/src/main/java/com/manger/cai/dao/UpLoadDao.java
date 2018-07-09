package com.manger.cai.dao;


import java.util.Map;

import org.springframework.stereotype.Component;

import com.manger.cai.mapper.UpLoadMapper;
import com.manger.core.dao.MybatisDao;
import com.manger.entity.cai.BaseImage;
import com.manger.entity.cai.Goods;
@Component
public class UpLoadDao extends MybatisDao<BaseImage>{
	public void addLogoImage(BaseImage bi){
		getMapper(UpLoadMapper.class).addLogoImage(bi);
	}

	public String selectLogo(Long logoMsgId) {
		// TODO Auto-generated method stub
		return getMapper(UpLoadMapper.class).selectLogo(logoMsgId);
	}

	public void insertGoods(Goods goods) {
		getMapper(UpLoadMapper.class).insertGoods(goods);
	}

	public void updateImage(Map<String, Object> map) {
		getMapper(UpLoadMapper.class).updateImage(map);
	}

	public void updateGoods(Map<String, Object> map2) {
		getMapper(UpLoadMapper.class).updateGoods(map2);
	}
}

package com.manger.cai.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.manger.cai.mapper.CaiMapper;
import com.manger.core.dao.MybatisDao;
import com.manger.entity.cai.Goods;
@Component
public class CaiDao extends MybatisDao<Goods>{

	public List<Map<String,Object>> huizongList() {
		// TODO Auto-generated method stub
		return getMapper(CaiMapper.class).huizongList();
	}

	public void insertAll(List<Goods> list) {
		getMapper(CaiMapper.class).insertAll(list);
}
}

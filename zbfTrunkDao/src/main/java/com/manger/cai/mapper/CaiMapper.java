package com.manger.cai.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.manger.core.dao.BaseMapper;
import com.manger.entity.cai.Goods;
@Repository
public interface CaiMapper extends BaseMapper<Goods>{

	List<Map<String, Object>> huizongList();
	
	public void insertAll(List<Goods> list);
	
}

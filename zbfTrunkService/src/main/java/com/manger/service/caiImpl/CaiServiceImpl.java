package com.manger.service.caiImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.manger.cai.dao.CaiDao;
import com.manger.cai.dao.DingDanDao;
import com.manger.core.entity.Page;
import com.manger.entity.cai.DingDan;
import com.manger.entity.cai.Goods;
@Component
public class CaiServiceImpl implements CaiService {
	
	@Autowired
	private CaiDao caiDao;
	
	@Autowired
	private DingDanDao dingDanDao;
	
	

	@Override
	public void findCaiPage(Page<Goods> page, Map<String, Object> filter) {
		page.setParams(filter);
		caiDao.findPage(page, "findCaiPage");
	}

	@Override
	public List<Map<String, Object>> huizongList() {
		// TODO Auto-generated method stub
		return caiDao.huizongList();
	}

	@Override
	public void dingDanList(Page<DingDan> page, Map<String, Object> filter) {
		page.setParams(filter);
		dingDanDao.findPage(page, "findDingDan");
		
	}

	@Override
	public void insertAll(List<Goods> list) {
		// TODO Auto-generated method stub
		caiDao.insertAll(list);
	}

}

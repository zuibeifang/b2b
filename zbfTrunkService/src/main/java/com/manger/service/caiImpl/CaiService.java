package com.manger.service.caiImpl;

import java.util.List;
import java.util.Map;

import com.manger.core.entity.Page;
import com.manger.entity.cai.DingDan;
import com.manger.entity.cai.Goods;

public interface CaiService {

	public void findCaiPage(Page<Goods> page, Map<String, Object> filter);

	public List<Map<String, Object>> huizongList();

	public void dingDanList(Page<DingDan> page, Map<String, Object> filter);

	public void insertAll(List<Goods> list);

}

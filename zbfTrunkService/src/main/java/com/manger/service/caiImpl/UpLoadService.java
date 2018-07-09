package com.manger.service.caiImpl;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.manger.entity.cai.Goods;

public interface UpLoadService {

	public Long uploadLogoImage(HttpServletRequest request, CommonsMultipartFile[] file) throws Exception;

	public String selectLogo(Long logoMsgId) throws Exception;

	public void insertGoods(Goods goods, Long logoMsgId)throws Exception;


}

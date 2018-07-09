package com.manger.service.caiImpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.manger.cai.dao.UpLoadDao;
import com.manger.core.utils.UID;
import com.manger.entity.cai.BaseImage;
import com.manger.entity.cai.Goods;
import com.manger.entity.user.User;
@Component
public class UpLoadServiceImpl implements UpLoadService  {
	
	@Autowired
	private UpLoadDao upLoadDao;

	@Override
	public Long uploadLogoImage(HttpServletRequest request,
			CommonsMultipartFile[] file) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		
		String datestr=sdf.format(new Date());
		User user=(User)request.getSession().getAttribute("user");
		//  /img/file   //  F:/demo/uploadImgFile
		
		//String realPath="F:/demo/uploadImgFile/logo"+datestr+"/";
		String realPath="D:/eclipse1.7/test/tankStyle/WebRoot/manger/images/";
		
		//String realPath="http://192.168.189.1:999/images/";
		//String virtualPath="/img/file/logo"+datestr+"/";
		String virtualPath="/images/";
		
		List<File> listFile=new ArrayList<File>();
		//向数据库写入图片信息
		Long yyy=0L;
		for(CommonsMultipartFile cmf:file){
			
			String fileName=cmf.getOriginalFilename().replace(" ","");
			
			File file0=new File(realPath);//创建一个文件夹
			if(!file0.exists()){
				file0.mkdirs();
			}
			File file1=new File(realPath+fileName);//创建一个文件
			if(file1.exists()){
				String[] str=fileName.split("\\.");
				
				fileName=str[0]+UID.next()+"."+str[1];
				
				file1=new File(realPath+fileName);
			}
			
			listFile.add(file1);
			
			//向数据库写入数据
			Map<String,Object> mapFile=new HashMap<String,Object>();
			Long id=UID.next();
			/*mapFile.put("id",id);
					mapFile.put("name",fileName);
			mapFile.put("virtualUrl", virtualPath+fileName);
			mapFile.put("url",realPath+fileName);
			mapFile.put("userId", user.getId());
			mapFile.put("goodsId", user.getId());
			upLoadDao.addLogoImage(mapFile);*/
			BaseImage bi = new BaseImage();
			bi.setId(id);
			bi.setName(fileName);
			bi.setUrl(realPath+fileName);
			bi.setUserId(user.getId());
			bi.setVersion(3);
			bi.setVirtualUrl(virtualPath+fileName);
			upLoadDao.addLogoImage(bi);
			yyy=id;
		}
		
		//向磁盘写数据（图片）
		for(int i=0;i<file.length;i++){
			
			file[i].transferTo(listFile.get(i));
			
		}
		
		return yyy;
	}

	@Override
	public String selectLogo(Long logoMsgId) throws Exception {
		
		
		return upLoadDao.selectLogo(logoMsgId);
	}

	@Override
	public void insertGoods(Goods goods, Long logoMsgId) throws Exception {
		// TODO Auto-generated method stub
		goods.setImgUrl("");
		upLoadDao.insertGoods(goods);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsId", goods.getId());
		map.put("id", logoMsgId);
		upLoadDao.updateImage(map);
		String url = upLoadDao.selectLogo(logoMsgId);
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("goodsId", goods.getId());
		map2.put("url", url);
		upLoadDao.updateGoods(map2);
	}


}

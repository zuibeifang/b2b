package com.manger.common.imgupload;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 上传图片相关的类
 * @author lcg
 * @date 2017-7-29
 */
public class ImageUpload {
   
   /**
    * 使用输入流生成缩略图
    * 设定尺寸，原比例缩放
    * @return
    */
   public static Boolean toSaveAndCreateSmallImgInStream1(List<InputStream> inputStreams,List<File> imagePaths,int chang,int kuan){
       try{
		   Thumbnails.fromInputStreams(inputStreams).size(chang,kuan).toFiles(imagePaths);
		   return true;
	   }catch(Exception e){
		   
	   }
	   return false;
   }
   /**
    *使用输入流生成缩略图
    *按比例缩放 
    * @param inputStreams
    * @param imagePaths
    * @param bili
    * @return
    */
   public static Boolean toSaveAndCreateSmallImgInStream2(List<InputStream> inputStreams,List<File> imagePaths,Double bili){
       try{
		   Thumbnails.fromInputStreams(inputStreams).scale(bili).toFiles(imagePaths);
		   return true;
	   }catch(Exception e){
		   
	   }
	   return false;
   }
   /**
    *使用文件对象生成缩略图
    *按照设定的长宽等比例缩放
    * @param inputStreams
    * @param imagePaths
    * @param bili
    * @return
    */
   public static Boolean toSaveAndCreateSmallImgFile1(List<File> files,List<File> imagePaths,int chang,int kuan){
       try{
		   Thumbnails.fromFiles(files).size(chang,kuan).toFiles(imagePaths);
		   return true;
	   }catch(Exception e){
		   
	   }
	   return false;
   }
   
   /**
    *使用文件对象生成缩略图
    *按比例缩放 
    * @param inputStreams
    * @param imagePaths
    * @param bili
    * @return
    */
   public static Boolean toSaveAndCreateSmallImgFile2(List<File> files,List<File> imagePaths,Double bili){
       try{
		   Thumbnails.fromFiles(files).scale(bili).toFiles(imagePaths);
		   return true;
	   }catch(Exception e){
		   
	   }
	   return false;
   }

}

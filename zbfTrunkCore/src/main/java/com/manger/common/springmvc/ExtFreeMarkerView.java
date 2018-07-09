package com.manger.common.springmvc;

/**
 * 
 */

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.manger.core.utils.ApplicationProperties;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * FreeMarkerView视图扩展
 * 
 * @author lcg
 *
 */
public class ExtFreeMarkerView extends FreeMarkerView {
	
	public static final String CONTEXT_PATH = "contextpath";
	public static final String STYLE_PATH = "stylePath";
	public static Long SERVER_TIME=null;
	static{
		if(SERVER_TIME==null){
			SERVER_TIME=System.currentTimeMillis();
		}
	}
	@Override
	protected void exposeHelpers(Map<String, Object> model,
			HttpServletRequest request) throws Exception {
		super.exposeHelpers(model, request);
		String contextPath=request.getContextPath();
		model.put(CONTEXT_PATH, contextPath);
		model.put(STYLE_PATH, ApplicationProperties.getValue("stylePath"));
		
	}
}

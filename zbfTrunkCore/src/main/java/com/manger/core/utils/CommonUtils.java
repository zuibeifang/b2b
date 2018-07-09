package com.manger.core.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.manger.common.sessionShare.XssShieldUtil;

public class CommonUtils {

	/**
	 * 获取request中的参数
	 * @param request
	 * @return
	 */
	public static Map<String,Object> getParameterMap(HttpServletRequest request) {  
        // 参数Map  
        Map<?,?> properties = request.getParameterMap();  
        // 返回值Map  
        Map<String,Object> returnMap = new HashMap<String,Object>();  
        Iterator<?> entries = properties.entrySet().iterator();  
        Entry<?, ?> entry;  
        String name = "";  
        String value = "";  
        while (entries.hasNext()) {  
            entry = (Entry<?, ?>) entries.next();  
            //name = (String) entry.getKey();  
            name =XssShieldUtil.stripXss((String) entry.getKey());
            Object valueObj = entry.getValue();  
            if(null == valueObj){  
                value = "";  
            }else if(valueObj instanceof String[]){  
                String[] values = (String[])valueObj;  
                for(int i=0;i<values.length;i++){  
                    value = values[i] + ",";  
                }  
                value = value.substring(0, value.length()-1);  
                value = XssShieldUtil.stripXss(value);
            }else{  
                value = valueObj.toString();
                value = XssShieldUtil.stripXss(value);
            }  
            returnMap.put(name, value);  
        }  
        return returnMap;  
    }  
}

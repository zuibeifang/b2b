package com.manger.common.sessionShare;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class ReHttpSessionListener implements HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println(event.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

}

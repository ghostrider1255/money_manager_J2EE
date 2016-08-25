package com.hibernate.action.interceptors;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class CheckLoginInterceptor implements Interceptor
{

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public String intercept(ActionInvocation aInterceptor) throws Exception {
		SessionMap<String,Object> sessionMap=(SessionMap<String,Object>)aInterceptor.getInvocationContext().getSession();
		System.out.println("*** Interceptor called ***");
		if(sessionMap==null || !sessionMap.containsKey("userName"))
		{
			return Action.LOGIN;
		}
		else
		{
			return aInterceptor.invoke();
		}
	}

}

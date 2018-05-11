package com.game.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** 
 * <li>ClassName:BeanFactory <br/> 
 * <li>@Description: TODO(类描述)
 * <li>@Date:     2016年9月20日 <br/> 
 * <li>@author  zzy       
 */
@Component
public class BeanFactory implements ApplicationContextAware{

	private static ApplicationContext context;
	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	public static Object getBean(Class<? extends Object> name){
		if(context!=null){
			return context.getBean(name);
		}
		return null;
	}
	
	public static MyConfig getConfig(){
		return (MyConfig)getBean(MyConfig.class);
	}
}

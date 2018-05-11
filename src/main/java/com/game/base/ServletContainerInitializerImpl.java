package com.game.base;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <li>ClassName:ServletContainerInitializerImpl <br/>
 * <li>@Description: TODO(类描述)
 * <li>@Date: 2016年9月19日 <br/>
 * <li>@author zzy
 */

public class ServletContainerInitializerImpl implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {
		ServletRegistration.Dynamic dynamic = servletContext.addServlet("DispatcherServlet", DispatcherServlet.class);
		dynamic.setLoadOnStartup(1);
		dynamic.addMapping("/");
		dynamic.setInitParameter("contextConfigLocation", "classpath:app-mvc.xml");
	}
}

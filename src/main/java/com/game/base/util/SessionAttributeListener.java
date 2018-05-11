package com.game.base.util;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import com.game.business.mapper.MgerMapper;
import com.game.business.model.manager.Manager;

public class SessionAttributeListener {
	//public class SessionAttributeListener implements HttpSessionListener{
	// 保存当前登录的所有用户
    private static Map<HttpSession, String> logonUsers = new HashMap<HttpSession, String>(); 
    // 用这个作为key, 在session中保存用户名.
    public static String SESSION_LOGIN_NAME = "logonUser";
    public void sessionCreated(HttpSessionEvent se) {
    }
   // 如果session超时, 则从map中移除这个用户
    public void sessionDestroyed(HttpSessionEvent se) {
        try {
            logonUsers.remove(se.getSession());
          //获取管理员离线时间 (秒)
            long Downtime=System.currentTimeMillis();
            System.out.println("管理员离线时间"+Downtime);
          //通过session获取管理员登录时间
           long Login_time= (Long) se.getSession().getAttribute("time");
           System.out.println("管理员登录时间"+Login_time);
           //获取管理员的在线时间
           long StageTime=(Downtime-Login_time)/60000;
            System.out.println("管理员在线时间"+StageTime);
            //
            Result result=new Result();
            MgerMapper mgerMapper = (MgerMapper) BeanFactory.getBean(MgerMapper.class);
            //获取当前用户名
            HttpSession session = se.getSession();
    		String admin = (String)session.getAttribute("admin");
    		
    		//判断数据库是否有此用户
    		Manager manager=mgerMapper.findByName(admin);
    		System.out.println(manager);
           if(manager==null){
        	   result.setMsg("用户名不存在");
           }
           manager.setOnlineTime((int) StageTime);
           manager.setStatus(3);
           manager.setHeapOnlineTime((int) (manager.getHeapOnlineTime()+StageTime));
          int count= mgerMapper.update(manager);
          if(count>0){
        	  result.setMsg("修改成功");
          }
        } catch (Exception e) {
        }
    }
}
/**
// 如果添加的属性是用户名, 则加入map中
public void attributeAdded(HttpSessionBindingEvent se) {
    if (se.getName().equals(SESSION_LOGIN_NAME)) { 
        logonUsers.put(se.getSession(), se.getValue().toString());
    }
}
public void attributeRemoved(HttpSessionBindingEvent se) {
	// 如果移除的属性是用户名, 则从map中移除
    if (se.getName().equals(SESSION_LOGIN_NAME)) { 
        try {
            logonUsers.remove(se.getSession());
        } catch (Exception e) {
        }
    }
}
// 如果改变的属性是用户名, 则跟着改变map
public void attributeReplaced(HttpSessionBindingEvent se) {
    if (se.getName().equals(SESSION_LOGIN_NAME)) {
        logonUsers.put(se.getSession(), se.getValue().toString());
    }
}
public static final boolean isLogonUser(String Name) {
    Set<HttpSession> keys = logonUsers.keySet();
    for (HttpSession key : keys) {
        if (logonUsers.get(key).equals(Name)) {
            return true;
        }
    }
    return false;
}*/
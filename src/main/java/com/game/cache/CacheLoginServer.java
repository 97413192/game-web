package com.game.cache;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import com.game.base.util.BeanFactory;
import com.game.constant.LoginRMIConstant;
import com.ranger.remote.LoginServerRMI;


public class CacheLoginServer {
	private static Logger logger = Logger.getLogger(CacheLoginServer.class);
	private static LoginServerRMI loginRMI = null;
	private static final String loginRMIUrl = BeanFactory.getConfig().getLoginRMIUrl();
	
	public static LoginServerRMI getLoginServer(){
		if(loginRMI == null){
			////////////////-----------连接login server rmi
			try {
				loginRMI = (LoginServerRMI)Naming.lookup(loginRMIUrl);
			} catch (MalformedURLException e) {
				logger.warn(e.getMessage());
			} catch (RemoteException e) {
				logger.warn(e.getMessage());
			} catch (NotBoundException e) {
				logger.warn(e.getMessage());
			}
		}else{
			try {
				loginRMI.exce(LoginRMIConstant.GM_TOOL_RMI_JOIN);
			} catch (RemoteException e) {
				logger.warn(e.getMessage());
				try {
					loginRMI = (LoginServerRMI)Naming.lookup(loginRMIUrl);
				} catch (MalformedURLException e1) {
					logger.warn(e1.getMessage());
				} catch (RemoteException e1) {
					logger.warn(e1.getMessage());
				} catch (NotBoundException e1) {
					logger.warn(e1.getMessage());
				}
			}
		}
		return loginRMI;
	}
}

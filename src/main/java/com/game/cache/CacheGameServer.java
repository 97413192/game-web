package com.game.cache;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import com.game.base.util.BeanFactory;
import com.game.constant.RMIConstant;

import cocl.rmi.GameRMIServer;


public class CacheGameServer {
	private static Logger logger = Logger.getLogger(CacheGameServer.class);
	private static GameRMIServer gameRMI = null;
	private static final String gameRMIUrl = BeanFactory.getConfig().getGameRMIUrl();
//	public static void main(String[] args) {
//		try {
//			Room room = new Room();
//			room.setBetBaseScore(1);
//			room.setBetMaxScore(2);
//			room.setBetMinScore(3);
//			room.setCreditRate(5);
//			room.setGameId(5);
//			room.setGoldLimit(6);
//			room.setId(7);
//			room.setName("doudizhi");
//			room.setPeopleNumLimit(10);
//			room.setVipLimit(11);
//			RMIResult result = new RMIResult(1);
//			result = (RMIResult)getGameServer().exec(LoginRMIConstant.GM_TOOL_ADD_ROOM,room.toBytes());
//			System.out.println("返回值："+result.getErrorCode());
//			//System.out.println("返回值："+getGameServer().exec(LoginRMIConstant.GM_TOOL_ADD_ROOM,room.toBytes()).toString());
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static GameRMIServer getGameServer(){
		System.out.println("gameRMIUrl = " + gameRMIUrl);
		if(gameRMI == null){
			try {
				gameRMI = (GameRMIServer)Naming.lookup(gameRMIUrl);
//				RMIResult result = (RMIResult)gameRMI.exec(LoginRMIConstant.GM_TOOL_ADD_ROOM,new Room().toBytes());
//				System.out.println(result);
			} catch (MalformedURLException e) {
				logger.warn(e.getMessage());
			} catch (RemoteException e) {
				logger.warn(e.getMessage());
			} catch (NotBoundException e) {
				logger.warn(e.getMessage());
			}
		}else{
			try {
				System.out.println("返回值："+gameRMI.exec(RMIConstant.GM_TOOL_GET_DESK_LIST,100));
			} catch (RemoteException e) {
				logger.warn(e.getMessage());
				try {
					gameRMI = (GameRMIServer)Naming.lookup(gameRMIUrl);
				} catch (MalformedURLException e1) {
					logger.warn(e1.getMessage());
				} catch (RemoteException e1) {
					logger.warn(e1.getMessage());
				} catch (NotBoundException e1) {
					logger.warn(e1.getMessage());
				}
			}
		}

		//System.out.println("=====gameRMIUrl = " + gameRMIUrl);
//		try {
//			System.out.println("返回值："+gameRMI.exec(RMIConstant.GM_TOOL_GET_DESK_LIST,100));
////			System.out.println("返回值："+gameRMI.exec(RMIConstant.GM_TOOL_FIND_SHARE_PRIZE));
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//System.out.println(gameRMI);
		return gameRMI;
	}
}

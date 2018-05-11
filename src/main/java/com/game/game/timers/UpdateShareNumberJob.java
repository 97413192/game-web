package com.game.game.timers;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.game.business.mapper.PlayerRealNameApplyMapper;
import com.game.business.mapper.PlayerShareInfoMapper;
import com.game.business.model.Share;

@Component
public class UpdateShareNumberJob implements UpdateShareNumberService {
	
		@Resource
		PlayerShareInfoMapper playerShareInfoMapper;
		
		@Resource
		PlayerRealNameApplyMapper playerRealNameApplyMapper;
		
		//每天0点更新分享次数
		@Scheduled(cron = "0 00 00 * * ?")
		public void udpateTask() {
			
			System.out.println("更新时间： " + new SimpleDateFormat().format(new Date()));
			System.out.println("******************************************************");
			
			Share share = new Share();
			share.setShareNumber(0);
			share.setShareTime(null);
			//playerShareInfoMapper.updateNumber(share);
			//直接删除记录
			playerShareInfoMapper.removeShareRecord();
		}
		
}

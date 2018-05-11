package com.game.base.util;

/**
 * 控制台打印计数器
 */
public class ConsolePercentCounter{
	
	private long size;
	
	private long percent = -1;
	
	private boolean init = true;
	
	private static String[] back_strs = new String[]{"\b\b","\b\b\b","\b\b\b"};
	
	
	public ConsolePercentCounter(long totalSize){
		size = totalSize;
	}
	
	
	public void trigger(long current){
		current = Math.min(size, current);
		long temp = current*100L/size;
		if(temp!=percent){
			long lastPercent = percent;
			int ix = 0;
			if(lastPercent<10)
				ix = 0;
			else if(lastPercent<100)
				ix = 1;
			else
				ix = 2;
			percent = temp;
			System.out.print((init?"":back_strs[ix])+percent+"%");
			if(init)
				init = false;
		}
	}
	
	public void reset(long size){
		this.size = size;
		percent = -1;
		init = true;
	}
	
	
	
	public static void main(String[] args) {
		
		ConsolePercentCounter c = new ConsolePercentCounter(1000);
		
		for(int i = 0; i < 1000;i ++){
			c.trigger(i);
		}
		
		
	}
	
	
}
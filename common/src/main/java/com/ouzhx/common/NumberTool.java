package com.ouzhx.common;

import java.util.Random;

/**
 * 数字工具类
 * @author Administrator
 *
 */
public class NumberTool {
	 private static Random rand = new Random(System.currentTimeMillis());
	 
	 /**
	  * 获取 >=min && <=max的随机【整数】
	  * @param min
	  * @param max
	  * @return
	  */
	  public static int randomInt(int min, int max) {
	     return min + (int)(Math.random() * (max - min));
	  }
	  
	  public static Random getRandom() {
	     return rand;
	  }
	  
	  /**
	  * 获取 >=min && <=max的随机【长整数】
	  * @param min
	  * @param max
	  * @return
	  */
	  public static long randomLong(long min, long max)  {
	     return  min + (long)(Math.random() * (max - min));
	  }
}

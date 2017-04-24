package com.ouzhx.common;

import java.security.MessageDigest;

/**
 * 加密工具类
 * @author Administrator
 *
 */
public class EncryptTool {
	
	/**
	 * MD5加密
	 * @param str
	 * @return 32位长度的加密字符串
	 */
	public final static String md5(String str) {
		String md5Str = null; // 返回字符串
		try {
			StringBuffer buf = new StringBuffer();  // 操作字符串
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());// 添加要进行计算摘要的信息,使用 plainText的 byte 数组更新摘要
			byte b[] = md.digest(); // 计算出摘要,完成哈希计算。
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				// 将整型 十进制 i 转换为16位，用十六进制参数表示的无符号整数值的字符串表示形式。
				buf.append(Integer.toHexString(i));
			}
			md5Str = buf.toString();  // 32位的加密
			// md5Str = buf.toString().md5Strstring(8,24); // 16位的加密
		}catch (RuntimeException e) {
		    throw e;
		  }  catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}  
}

package com.jk.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	/**
	 * 生成MD5加密值
	 * 
	 * @param tx
	 * @return
	 * @throws Exception
	 */
	public static String md5(String tx) throws Exception {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(tx.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("没有md5这个算法！");
		}
		// 16进制数字
		String md5code = new BigInteger(1, secretBytes).toString(16);
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}
}

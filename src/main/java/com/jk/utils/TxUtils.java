package com.jk.utils;

public class TxUtils {

	/**
	 * 判断字符串是否为null或空字符串
	 * 
	 * @param tx
	 * @return
	 */
	public static Boolean isEmpty(String tx) {
		if (null == tx || "".equals(tx.trim())) {
			return true;
		} else {
			return false;
		}

	}

}

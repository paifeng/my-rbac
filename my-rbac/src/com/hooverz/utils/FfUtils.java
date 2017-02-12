package com.hooverz.utils;

import java.util.UUID;

public class FfUtils {

	/**
	 * 获取一个32位的随机ID
	 * 
	 * @param key
	 * @return
	 */
	public static String getRandomId(String key) {
		return UUID.nameUUIDFromBytes(key.toString().getBytes()).toString()
				.replace("-", "").toString();
	}

}

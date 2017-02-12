package com.hooverz.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密编码工具
 * 
 * @author HaiFeng
 * 
 */
public class EncryptionUtils {

	public static String md5(String plainText) { // 明文
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	/**
	 * base64编码
	 * 
	 * @param plainText
	 * @return
	 */
	public static String base64encode(String plainText) {
		return new BASE64Encoder().encode(plainText.getBytes());
	}

	/**
	 * base64解码
	 * 
	 * @param plainText
	 * @return
	 */
	public static String base64decode(String plainText) {
		BASE64Decoder decoder = new BASE64Decoder();

		try {
			return new String(decoder.decodeBuffer(plainText));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

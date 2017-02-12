package com.hooverz.el;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CustomElFunction {

	/**
	 * url解码
	 * 
	 * @param str
	 *            　待解码字符串
	 * @param enc
	 *            　编码
	 * @return 返回解码后的字符串
	 */
	public static String _urlDecoder(String str, String enc) {

		try {
			return URLDecoder.decode(str, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}
}

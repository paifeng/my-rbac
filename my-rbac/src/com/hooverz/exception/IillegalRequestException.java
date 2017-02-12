package com.hooverz.exception;

/**
 * 非法请求异常
 * 
 * @author love5
 * 
 */
public class IillegalRequestException extends Exception {

	private static final long serialVersionUID = -7451931002001654878L;

	public IillegalRequestException(String msg) {
		super(msg);
	}
}

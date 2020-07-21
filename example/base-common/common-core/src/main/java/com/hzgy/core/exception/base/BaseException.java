package com.hzgy.core.exception.base;

public abstract class BaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BaseException(String code, Throwable t) {
		super(code, t);
	}

	public BaseException(String code) {
		super(code);
	}
}
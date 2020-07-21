package com.hzgy.core.exception;

import com.hzgy.core.exception.base.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOException extends BaseException {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(DAOException.class);

	public DAOException(String msg, Throwable t) {
		super(msg, t);
		logger.info("dao exception:" + msg,t);
	}

	public DAOException(String msg) {
		super(msg);
		logger.info("dao exception:" + msg);
	}
}
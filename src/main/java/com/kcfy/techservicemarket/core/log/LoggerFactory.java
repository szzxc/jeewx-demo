/**
 * 
 */
package com.kcfy.techservicemarket.core.log;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zhengzw@fengyuntec.com
 * LOGGER FACTORY
 */
public class LoggerFactory {
	private static String LOGGER_NAME_DEFAULT = "info_logger";

	/**
	 * 获取logger
	 * @param level
	 * @return
	 */
	public static Logger getLogger(LevelEnum level) {

		Logger logger = null;

		switch (level) {
		case INFO:
			logger = getInfoLogger();
			break;

		default:
			logger = getInfoLogger();
			break;
		}

		return logger;
	}

	/**
	 * 获取INFO级别LOGGER
	 * 
	 * @return
	 */
	private static Logger getInfoLogger() {
		Logger logger = Logger.getLogger(LOGGER_NAME_DEFAULT);

		logger.setLevel(Level.INFO);

		return logger;
	}
}

/**
 * 
 */
package com.kcfy.techservicemarket.core.util;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import com.kcfy.techservicemarket.core.dto.BaseSupportObject;
import com.kcfy.techservicemarket.core.log.LevelEnum;
import com.kcfy.techservicemarket.core.log.LoggerFactory;


/**
 * @author zhengzw@fengyuntec.com
 *
 */
public class HttpUtils {
	private static Logger logger = LoggerFactory.getLogger(LevelEnum.INFO);

	private static String EMPTY = "";
	private static String AND = "&";
	private static String MARK = "?";
	private static String EQUAL = "=";

	/**
	 * 获取post参数：key=value&key=value&key=value
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static String getPostParameter(BaseSupportObject object) throws Exception {

		if (null == object) {
			return EMPTY;
		}

		StringBuilder builder = new StringBuilder();

		Field[] fields = object.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);

			builder.append(AND).append(field.getName()).append(EQUAL).append(String.valueOf(field.get(object)));
		}

		String parameter = builder.toString().substring(1);

		logger.info("INFO:**********Thread [" + Thread.currentThread().getId() + "] tranform parameter : " + parameter
				+ "**********");

		return parameter;
	}

	/**
	 * 获取get参数?key=value&key=value&key=value
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static String getGetParameter(BaseSupportObject object) throws Exception {
		StringBuilder builder = new StringBuilder(MARK);

		String parameter = builder.append(getPostParameter(object)).toString();

		logger.info("INFO:**********Thread [" + Thread.currentThread().getId() + "] tranform parameter : " + parameter
				+ "**********");

		return parameter;
	}
}

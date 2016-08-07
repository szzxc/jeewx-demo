/**
 * 
 */
package com.kcfy.techservicemarket.core.generate;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.kcfy.techservicemarket.core.log.LevelEnum;
import com.kcfy.techservicemarket.core.log.LoggerFactory;

/**
 * @author zhengzw@fengyuntec.com
 * property 配置文件生成器
 */
public class PropertyGen {

	private static Logger logger = LoggerFactory.getLogger(LevelEnum.INFO);

	private static String FILENAME = "token.properties";

	public static Properties prop = new Properties();

	/**
	 * 加载token.properties配置文件
	 * 
	 * @throws Exception
	 */
	public static void loadProperties() throws Exception {
		synchronized (PropertyGen.class) {
			if (prop.isEmpty()) {

				logger.info(
						"INFO:**********Thread [" + Thread.currentThread().getId() + "] loaded properties**********");

				InputStream inStream = getPropertyPath();
				prop.load(inStream);

				inStream.close();
			}
		}
	}

	/**
	 * 获取配置文件
	 * 
	 * @return
	 */
	private static InputStream getPropertyPath() throws Exception {
		InputStream is = null;
		
		try{
			//先从class path 下读取配置文件，如果读取不到，则读取local lib
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(FILENAME);
			
			if(null == is){
				throw new Exception();
			}
			
			logger.info("INFO:**********Thread [" + Thread.currentThread().getId() 
				+ "] loaded properties from class path**********");
			
		}catch(Exception e){
			is = PropertyGen.class.getResourceAsStream(FILENAME);
			
			logger.info("INFO:**********Thread [" + Thread.currentThread().getId() 
					+ "] loaded properties from local lib **********");
		}
		
		return is;
	}
}

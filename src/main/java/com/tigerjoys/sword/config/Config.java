package com.tigerjoys.sword.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static Logger logger = LogManager.getLogger();
	private static Properties prop = new Properties();
	static {
		try {
			prop.load(Config.class.getClassLoader().getResourceAsStream("fields.configuration.properties"));
		} catch (IOException e) {
			logger.error("配置文件加载失败" + e.getMessage());
		}
	}
	public static int LOG_JSON_TYPE = Integer.parseInt(prop.getProperty("LOG_JSON_TYPE"));
	public static int LOG_RUBY_TYPE = Integer.parseInt(prop.getProperty("LOG_RUBY_TYPE"));
	public static int LOG_UNKNOWN_TYPE = Integer.parseInt(prop.getProperty("LOG_UNKNOWN_TYPE"));
	public static int JSON_0 = Integer.parseInt(prop.getProperty("JSON_0"));
	public static int JSON_1 = Integer.parseInt(prop.getProperty("JSON_1"));
	public static int RUBY_0 = Integer.parseInt(prop.getProperty("RUBY_0"));
	public static int RUBY_1 = Integer.parseInt(prop.getProperty("RUBY_1"));
	
	public static int DATA_BASE_INDEX = Integer.parseInt(prop.getProperty("DATA_BASE_INDEX"));
	public static int DATA_FILE_INDEX = Integer.parseInt(prop.getProperty("DATA_FILE_INDEX"));
	
	public static int BEFOR_LEFT_BRACE = Integer.parseInt(prop.getProperty("BEFOR_LEFT_BRACE"));
	public static int AFTER_LEFT_BRACE = Integer.parseInt(prop.getProperty("AFTER_LEFT_BRACE"));
	
	public static int RECORD_TIME = Integer.parseInt(prop.getProperty("RECORD_TIME"));
	public static int DEVS_WITH_TIME = Integer.parseInt(prop.getProperty("DEVS_WITH_TIME"));
	public static int DEVS_WITHOUT_TIME = Integer.parseInt(prop.getProperty("DEVS_WITHOUT_TIME"));
	public static int MEM_FREE = Integer.parseInt(prop.getProperty("MEM_FREE"));
	public static int PKGN = Integer.parseInt(prop.getProperty("PKGN"));
	public static int SV = Integer.parseInt(prop.getProperty("SV"));
	public static int IMSI1 = Integer.parseInt(prop.getProperty("IMSI1"));
	public static int APIL = Integer.parseInt(prop.getProperty("APIL"));
	public static int MEM_TOTAL = Integer.parseInt(prop.getProperty("MEM_TOTAL"));
	public static int _ID = Integer.parseInt(prop.getProperty("_ID"));
	public static int EVENTID = Integer.parseInt(prop.getProperty("EVENTID"));
	public static int DID = Integer.parseInt(prop.getProperty("DID"));
	public static int SYSTEM_FREE_SPACE = Integer.parseInt(prop.getProperty("SYSTEM_FREE_SPACE"));
	public static int TAG = Integer.parseInt(prop.getProperty("TAG"));
	public static int TIMESTAMP = Integer.parseInt(prop.getProperty("TIMESTAMP"));
	public static int DATA_FREE_SPACE = Integer.parseInt(prop.getProperty("DATA_FREE_SPACE"));
	public static int UUID = Integer.parseInt(prop.getProperty("UUID"));
	public static int IMEI1 = Integer.parseInt(prop.getProperty("IMEI1"));
	public static int LINUX_V = Integer.parseInt(prop.getProperty("LINUX_V"));
	
	

	public static String COMMA_SEPERATOR = prop.getProperty("COMMA_SEPERATOR");
	public static String LEFT_BRACE = prop.getProperty("LEFT_BRACE");
	public static String RAW_DATA_PATH = prop.getProperty("RAW_DATA_PATH");
	
	
	
	public static String[] DEVS_LOG_FLAGS = splitFieldsToArray(prop.getProperty("DEVS_LOG_FLAGS"),Config.COMMA_SEPERATOR);
	public static String[] FIELDS = splitFieldsToArray(prop.getProperty("FIELDS"),Config.COMMA_SEPERATOR);
	
	private static  String[] splitFieldsToArray(String Fields,String seperator){
		String[] fields = Fields.split(seperator); 
		return fields;
	}

	

}

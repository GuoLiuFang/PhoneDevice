package com.tigerjoys.sword.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.tigerjoys.sword.common.base.CommonKits;
import java.util.Properties;

public class DevsConfig {

	private static Logger logger = LogManager.getLogger();
	private static Properties prop = new Properties();
	static {
		try {
			prop.load(DevsConfig.class.getClassLoader().getResourceAsStream(
					"devs.configuration.properties"));
		} catch (Exception e) {
			logger.error("配置文件加载失败" + new CommonKits().getErrorMessage(e));
		}
	}
	// Type alias index
	public static int TYPE_ALIAS_INDEX_JSON_0 = Integer.parseInt(prop
			.getProperty("TYPE_ALIAS_INDEX_JSON_0"));
	public static int TYPE_ALIAS_INDEX_JSON_1 = Integer.parseInt(prop
			.getProperty("TYPE_ALIAS_INDEX_JSON_1"));
	public static int TYPE_ALIAS_INDEX_RUBY_0 = Integer.parseInt(prop
			.getProperty("TYPE_ALIAS_INDEX_RUBY_0"));
	public static int TYPE_ALIAS_INDEX_RUBY_1 = Integer.parseInt(prop
			.getProperty("TYPE_ALIAS_INDEX_RUBY_1"));
	public static int INDEX_FRONT_SEGMENTS = Integer.parseInt(prop
			.getProperty("INDEX_FRONT_SEGMENTS"));
	public static int INDEX_BACK_SEGMENTS = Integer.parseInt(prop
			.getProperty("INDEX_BACK_SEGMENTS"));
	public static int INDEX_METHOD_NAME = Integer.parseInt(prop
			.getProperty("INDEX_METHOD_NAME"));
	public static int INDEX_METHOD_RESOURCES = Integer.parseInt(prop
			.getProperty("INDEX_METHOD_RESOURCES"));
	// index fields
	public static String BAD_FIELD_TIME_STAMP = prop
			.getProperty("BAD_FIELD_TIME_STAMP");
	// methods
	public static String HANDLER_NAME_LOG_JSON = prop.getProperty("HANDLER_NAME_LOG_JSON");
	public static String HANDLER_RESOURCES_LOG_JSON = prop.getProperty("HANDLER_RESOURCES_LOG_JSON");
	public static String HANDLER_NAME_LOG_RUBY = prop.getProperty("HANDLER_NAME_LOG_RUBY");
	public static String HANDLER_RESOURCES_LOG_RUBY = prop.getProperty("HANDLER_RESOURCES_LOG_RUBY");
	public static String HANDLER_NAME_LOG_UNKNOWN = prop.getProperty("HANDLER_NAME_LOG_UNKNOWN");
	public static String HANDLER_RESOURCES_LOG_NULL = prop.getProperty("HANDLER_RESOURCES_LOG_NULL");
	// seperators
	public static String SEPERATOR_VERTICAL_BAR = prop
			.getProperty("SEPERATOR_VERTICAL_BAR");
	public static String SEPERATOR_LEFT_BRACE = prop
			.getProperty("SEPERATOR_LEFT_BRACE");
	public static String SEPERATOR_RIGHT_ARROW = prop
			.getProperty("SEPERATOR_RIGHT_ARROW");
	public static String SEPERATOR_COMMA = prop.getProperty("SEPERATOR_COMMA");
	public static String SEPERATOR_COLON = prop.getProperty("SEPERATOR_COLON");
	public static String SEPERATOR_INFO = prop.getProperty("SEPERATOR_INFO");
	public static String SEPERATOR_NULL = prop.getProperty("SEPERATOR_NULL");
	// paths
	public static String PATH_RAW_DATA = prop.getProperty("PATH_RAW_DATA");
	public static String PATH_DATA_BASE = prop.getProperty("PATH_DATA_BASE");
	public static String PATH_DATA_FILE = prop.getProperty("PATH_DATA_FILE");
	// java-reflection fields
	public static String FIELDS_RECORD_TIME = prop
			.getProperty("FIELDS_RECORD_TIME");
	public static String FIELDS_APIL = prop.getProperty("FIELDS_APIL");
	public static String FIELDS_UUID = prop.getProperty("FIELDS_UUID");
	public static String FIELDS_IMSI1 = prop.getProperty("FIELDS_IMSI1");
	public static String FIELDS_LINUX_V = prop.getProperty("FIELDS_LINUX_V");
	public static String FIELDS__ID = prop.getProperty("FIELDS__ID");
	public static String FIELDS_A = prop.getProperty("FIELDS_A");
	public static String FIELDS_REASONS = prop.getProperty("FIELDS_REASONS");
	public static String FIELDS_D = prop.getProperty("FIELDS_D");
	public static String FIELDS_D_REASONS_DEVS = prop.getProperty("FIELDS_D_REASONS_DEVS");
	public static String FIELDS_EVENT_ID = prop.getProperty("FIELDS_EVENT_ID");
	public static String FIELDS_C = prop.getProperty("FIELDS_C");
	public static String FIELDS_TIME_STAMP = prop
			.getProperty("FIELDS_TIME_STAMP");
	public static String FIELDS_B = prop.getProperty("FIELDS_B");
	public static String FIELDS_DID = prop.getProperty("FIELDS_DID");
	public static String FIELDS_SV = prop.getProperty("FIELDS_SV");
	public static String FIELDS_TAG = prop.getProperty("FIELDS_TAG");
	public static String FIELDS_IMEI1 = prop.getProperty("FIELDS_IMEI1");
	public static String FIELDS_PKGN = prop.getProperty("FIELDS_PKGN");
	public static String FIELDS_MEM_TOTAL = prop.getProperty("FIELDS_MEM_TOTAL");
	public static String FIELDS_DATA_FREE_SPACE = prop.getProperty("FIELDS_DATA_FREE_SPACE");
	public static String FIELDS_MEM_FREE = prop.getProperty("FIELDS_MEM_FREE");
	public static String FIELDS_SYSTEM_FREE_SPACE = prop.getProperty("FIELDS_SYSTEM_FREE_SPACE");
	// regular expression
	public static String REGEX_FIND_DATETIME = prop
			.getProperty("REGEX_FIND_DATETIME");
	public static String REGEX_FIND_RECORD_TIME = prop
			.getProperty("REGEX_FIND_RECORD_TIME");
	public static String REGEX_FORMAT_DATETIME = prop
			.getProperty("REGEX_FORMAT_DATETIME");
	public static String REGEX_ENTER = prop.getProperty("REGEX_ENTER");
	// 标示字段，别名数组
	public static String[] TYPE_ALIAS_ARRAY = prop.getProperty("TYPE_ALIAS_ARRAY").split(DevsConfig.SEPERATOR_COMMA);

}

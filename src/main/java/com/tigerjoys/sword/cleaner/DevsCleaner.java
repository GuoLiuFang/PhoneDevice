package com.tigerjoys.sword.cleaner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tigerjoys.sword.config.Config;

public class DevsCleaner {
	private static Logger logger = LogManager.getLogger();
	
	public String clean(String line){
		String result = "";
		int log_type = getLogType(line);
		
		if (log_type == Config.LOG_JSON_TYPE) {
			result = cleanJson(line);
		}
		if (log_type == Config.LOG_RUBY_TYPE) {
			result = cleanRuby(line);
		}
		if (log_type == Config.LOG_UNKNOWN_TYPE) {
			result = "";
		}
		return result;
	}//clean
	
	
	private String cleanRuby(String line) {
		// TODO Auto-generated method stub
		return null;
	}


	private String cleanJson(String line) {
		// TODO Auto-generated method stub
		return null;
	}


	private static int getLogType(String line){
		if(line.contains(Config.DEVS_LOG_FLAGS[Config.JSON_0]) || line.contains(Config.DEVS_LOG_FLAGS[Config.JSON_1])){
			return Config.LOG_JSON_TYPE;
		}
		if(line.contains(Config.DEVS_LOG_FLAGS[Config.RUBY_0]) || line.contains(Config.DEVS_LOG_FLAGS[Config.RUBY_1])){
			return Config.LOG_RUBY_TYPE;
		}
		return Config.LOG_UNKNOWN_TYPE;
	}//getLogType
	



}//DevsCleaner

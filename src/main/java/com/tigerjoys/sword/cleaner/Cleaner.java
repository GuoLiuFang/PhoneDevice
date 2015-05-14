package com.tigerjoys.sword.cleaner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import scala.reflect.internal.Trees.This;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tigerjoys.sword.config.Config;
import com.tigerjoys.sword.model.DevsLog;

public class Cleaner {
	private static Logger logger = LogManager.getLogger();
	//传递过来的数据trim后肯定不为空.现在都假设数据一切正常为前提。调试的时候在修改
	public String clean(String line){
		String result = "";
		String log_type = getLogType(line);
		try {
			
			Method method = this.getClass().getDeclaredMethod(log_type,String.class);//为空的时候是new Class<?>[0]
			result = (String) method.invoke(this, line);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} 
		
		return result;
		
	}//clean
	
	
	private String cleanRuby(String line) {
		//取前后段，把后端的数据转化为Json
		String[] front_segments_back_segments = splitLine(line, Config.SEPERATOR_LEFT_BRACE);
		String front_result = "";
		String back_result = "";
		StringBuffer sb = new StringBuffer();
		front_result = getFront(front_segments_back_segments[Config.INDEX_FRONT_SEGMENTS]);
		back_result = getBack(front_segments_back_segments[Config.INDEX_BACK_SEGMENTS]);
		sb.append(front_result).append(Config.SEPERATOR_VERTICAL_BAR).append(back_result);
		return sb.toString();
	}

	private String getBack(String back_segments) {
		//把所有的=>替换成冒号：,并删除timestamp这个属性。
		back_segments = back_segments.replaceAll(Config.SEPERATOR_RIGHT_ARROW, Config.SEPERATOR_COLON);
		int index_bad_begin = back_segments.indexOf(Config.BAD_FIELD_TIME_STAMP);
		int index_bad_end = back_segments.indexOf(Config.SEPERATOR_COMMA, index_bad_begin + Config.BAD_FIELD_TIME_STAMP.length());
		String remove_bad_string = back_segments.substring(index_bad_begin, index_bad_end);//是包含的
		back_segments = back_segments.replace(remove_bad_string, Config.SEPERATOR_NULL);
		JsonElement root = new JsonParser().parse(back_segments);
		String result = "";
		DevsLog devs_log = new DevsLog();
		StringBuffer sb = new StringBuffer();
		for (String field : Config.DEVS_FIELDS) {
			
			try {
				Method method = devs_log.getClass().getMethod(field, JsonElement.class);//为空的时候是new Class<?>[0]
				result = (String) method.invoke(devs_log, root);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
			sb.append(result).append(Config.SEPERATOR_VERTICAL_BAR);
			
		}//for--java--refelction
		
		return sb.substring(0, sb.length() - 1);
	}


	private String getFront(String front_segments) {
		String record_time = "";
		int index_INFO = front_segments.indexOf(Config.SEPERATOR_INFO);
		record_time = front_segments.substring(0, index_INFO).trim();
		return record_time;
	}


	private String[] splitLine(String line, String seperator) {
		int index_seperator = line.indexOf(seperator);
		String[] segments = new String[2];
		segments[Config.INDEX_FRONT_SEGMENTS] = line.substring(0, index_seperator);
		segments[Config.INDEX_BACK_SEGMENTS] = line.substring(index_seperator + seperator.length(), line.length());
		return segments;
	}



	private String cleanJson(String line) {
		// TODO Auto-generated method stub
		return null;
	}


	private String getLogType(String line){
		//对于2014年8月13日到11月26日的两种日志类型算是解析完毕。
		if(line.contains(Config.TYPE_ALIAS_ARRAY[Config.TYPE_ALIAS_INDEX_JSON_0]) || line.contains(Config.TYPE_ALIAS_ARRAY[Config.TYPE_ALIAS_INDEX_JSON_1])){
			return Config.TYPE_LOG_JSON;
		}
		if(line.contains(Config.TYPE_ALIAS_ARRAY[Config.TYPE_ALIAS_INDEX_RUBY_0]) || line.contains(Config.TYPE_ALIAS_ARRAY[Config.TYPE_ALIAS_INDEX_RUBY_1])){
			return Config.TYPE_LOG_RUBY;
		}
		return Config.TYPE_LOG_UNKNOWN;
	}//getLogType


	public String chooseDataBaseFields(String[] database_datafile) {
		// TODO Auto-generated method stub
		return null;
	}


	public String chooseDataFileFields(String[] database_datafile) {
		// TODO Auto-generated method stub
		return null;
	}
	



}//DevsCleaner

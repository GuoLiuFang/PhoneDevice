package com.tigerjoys.sword.cleaner;

import java.lang.reflect.Method;

import org.apache.hadoop.conf.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tigerjoys.sword.common.base.CommonKits;
import com.tigerjoys.sword.config.DevsConfig;
import com.tigerjoys.sword.model.DevsLog;

public class DevsCleaner {
	private static Logger logger = LogManager.getLogger();
	private CommonKits common_kits = null;
	private DevsLog devs_log = null;

	public DevsCleaner() {
		this.common_kits = new CommonKits();
		this.devs_log = new DevsLog();
	}

	public String clean(String line) {
		String result = "";
		String[] handler = getHandler(line);
		try {
			Method method = this.getClass().getDeclaredMethod(handler[DevsConfig.INDEX_METHOD_NAME],
					String.class, String.class);
			result = (String) method.invoke(this, line, handler[DevsConfig.INDEX_METHOD_RESOURCES]);
		} catch (Exception e) {
			logger.error(common_kits.getErrorMessage(e));
		}
		return result;
	}

	private String cleanRuby(String line, String resources) {
		String result = "";
		line = convertRubyToJson(line);
		result = cleanGeneralLog(line, resources);
//		result = setTag(result);
		return result;
	}

	private String cleanJson(String line, String resources) {
		String result = "";
		result = cleanGeneralLog(line,resources);
		return result;
	}

	private String cleanGeneralLog(String line, String resources) {
		String[] front_segments_back_segments = splitLine(line,
				DevsConfig.SEPERATOR_LEFT_BRACE);
		String result = "";
		String front_result = "";
		String back_result = "";
		StringBuffer sb = new StringBuffer();
		front_result = common_kits.getRecordTime(front_segments_back_segments[DevsConfig.INDEX_FRONT_SEGMENTS]);
		back_result = getBack(front_segments_back_segments[DevsConfig.INDEX_BACK_SEGMENTS], resources);
		sb.append(front_result).append(DevsConfig.SEPERATOR_VERTICAL_BAR)
				.append(back_result);
		result = sb.toString();
		return result;
	}

	private String convertRubyToJson(String back_segments) {
		back_segments = back_segments.replaceAll(
				DevsConfig.SEPERATOR_RIGHT_ARROW, DevsConfig.SEPERATOR_COLON);
		int index_bad_begin = back_segments
				.indexOf(DevsConfig.BAD_FIELD_TIME_STAMP);
		int index_bad_end = back_segments.indexOf(DevsConfig.SEPERATOR_COMMA,
				index_bad_begin);
		// index_bad_end + 1加一是为了把逗号包括在内
		String remove_bad_string = back_segments.substring(index_bad_begin,
				index_bad_end + 1);
		back_segments = back_segments.replace(remove_bad_string,
				DevsConfig.SEPERATOR_NULL);
		return back_segments;
	}

	private String getBack(String back_segments, String resources) {
		JsonElement root = new JsonParser().parse(back_segments);
		String result = "";
		String field_result = "";
		StringBuffer sb = new StringBuffer();
		String[] fields = resources.split(DevsConfig.SEPERATOR_COMMA);
		for (String field : fields) {
			try {
				Method method = devs_log.getClass().getDeclaredMethod(field,
						JsonElement.class);
				field_result = (String) method.invoke(devs_log, root);
			} catch (Exception e) {
				logger.error(common_kits.getErrorMessage(e));
			}
			sb.append(field_result).append(DevsConfig.SEPERATOR_VERTICAL_BAR);
		}
		result = sb.substring(0, sb.length() - 1);
		return result;
	}

	private String[] splitLine(String line, String seperator) {
		int index_seperator = line.indexOf(seperator);
		String[] segments = new String[2];
		segments[DevsConfig.INDEX_FRONT_SEGMENTS] = line.substring(0,
				index_seperator);
		segments[DevsConfig.INDEX_BACK_SEGMENTS] = line.substring(
				index_seperator, line.length());
		return segments;
	}

	private String[] getHandler(String line) {
		String[] result = new String[2];
		result[0] = DevsConfig.HANDLER_NAME_LOG_UNKNOWN;
		result[1] = DevsConfig.HANDLER_RESOURCES_LOG_NULL;
		if (line.contains(DevsConfig.TYPE_ALIAS_ARRAY[DevsConfig.TYPE_ALIAS_INDEX_JSON_0])
				|| line.contains(DevsConfig.TYPE_ALIAS_ARRAY[DevsConfig.TYPE_ALIAS_INDEX_JSON_1])) {
			result[0] = DevsConfig.HANDLER_NAME_LOG_JSON;
			result[1] = DevsConfig.HANDLER_RESOURCES_LOG_JSON;
			return result;
		}
		if (line.contains(DevsConfig.TYPE_ALIAS_ARRAY[DevsConfig.TYPE_ALIAS_INDEX_RUBY_0])
				|| line.contains(DevsConfig.TYPE_ALIAS_ARRAY[DevsConfig.TYPE_ALIAS_INDEX_RUBY_1])) {
			result[0] = DevsConfig.HANDLER_NAME_LOG_RUBY;
			result[1] = DevsConfig.HANDLER_RESOURCES_LOG_RUBY;
			return result;
		}
		return result;
	}

	public String chooseDataBaseFields(String[] database_datafile) {
		// TODO Auto-generated method stub
		return null;
	}

	public String chooseDataFileFields(String[] database_datafile) {
		// TODO Auto-generated method stub
		return null;
	}

}

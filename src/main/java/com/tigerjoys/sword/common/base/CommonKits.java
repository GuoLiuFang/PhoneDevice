package com.tigerjoys.sword.common.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.io.PatternFilenameFilter;
import com.tigerjoys.sword.config.DevsConfig;

public class CommonKits {
	private static Logger logger = LogManager.getLogger();
	private StringWriter sw = null;
	private PrintWriter pw = null;

	public CommonKits() {
		sw = new StringWriter();
		pw = new PrintWriter(sw);
	}

	public String getErrorMessage(Exception e) {
		e.printStackTrace(this.pw);
		return this.sw.toString();
	}
	
	public String getRecordTime(String line) {
		String result = "";
		Pattern pattern = Pattern.compile(DevsConfig.REGEX_FIND_RECORD_TIME);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			result = matcher.group();
		}
		return result;
	}

//	public String getDevsWithoutTime(String raw_devs) {
//		String result = "";
//		result = raw_devs.replaceAll(DevsConfig.REGEX_FIND_DATETIME,
//				DevsConfig.SEPERATOR_NULL);
//		return result;
//	}

	public String getMd5(String raw_devs) {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(raw_devs.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			result = sb.toString();
		} catch (Exception e) {
			logger.error(this.getErrorMessage(e));
		}
		return result;
	}

	public String formatDevsString(String raw_devs) {
		String result = "";
		String[] devs_lines = raw_devs.split(DevsConfig.REGEX_ENTER);
		StringBuffer sb_line = new StringBuffer();
		Pattern pattern = Pattern.compile(DevsConfig.REGEX_FIND_DATETIME);
		Matcher matcher = null;
		for (String line : devs_lines) {
			matcher = pattern.matcher(line);
			String date_time = "";
			while (matcher.find()) {
				date_time = matcher.group();
			}
			StringBuffer sb_element = new StringBuffer();
			if (StringUtils.isNotEmpty(date_time)) {
				int index_date_time_begin = line.indexOf(date_time);
				String permission_owner_group_string = line.substring(0, index_date_time_begin);
				String[] line_elements = permission_owner_group_string.split(DevsConfig.SEPERATOR_TAB);
				for (int i = 0, j = 0; i < line_elements.length && j < 3; i++) {
					if (StringUtils.isNotEmpty(line_elements[i])) {
						sb_element.append(line_elements[i]).append(DevsConfig.SEPERATOR_COMMA);
						j++;
					}
				}
				String name = line.substring(index_date_time_begin + date_time.length(), line.length() );
				sb_element.append(date_time).append(DevsConfig.SEPERATOR_COMMA).append(name.trim()).append(DevsConfig.REGEX_ENTER);
			}
			sb_line.append(sb_element.toString());
		}
		result = sb_line.toString();
		return result;
	}

}

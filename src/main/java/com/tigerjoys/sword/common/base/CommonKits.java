package com.tigerjoys.sword.common.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public String getDevsWithoutTime(String raw_devs) {
		String result = "";
		result = raw_devs.replaceAll(DevsConfig.REGEX_FIND_DATETIME,
				DevsConfig.SEPERATOR_NULL);
		return result;
	}

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

}

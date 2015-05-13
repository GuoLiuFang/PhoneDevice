package com.tigerjoys.sword.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonElement;
import com.tigerjoys.sword.config.Config;

public class DevsLog {
	private static Logger logger = LogManager.getLogger();



	public String getApil(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(Config.FIELDS_APIL).getAsString();
		return result;
	}

	public String getUuid(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(Config.FIELDS_UUID).getAsString();
		return result;
	}

	public String getImsi1(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(Config.FIELDS_IMSI1).getAsString();
		return result;
	}

	public String getLinux_v(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(Config.FIELDS_LINUX_V).getAsString();
		return result;
	}

	public String get_id(JsonElement root) {
		String result = "";
		if (root.getAsJsonObject().get(Config.FIELDS_IMSI1) != null) {
			result = root.getAsJsonObject().get(Config.FIELDS__ID).getAsString();
		} else {
			result = root.getAsJsonObject().get(Config.FIELDS_A).getAsString();
		}
		return result;
	}

	public String getDevs_md5(JsonElement root) {
		String result = "";
		String raw_devs = root.getAsJsonObject().get(Config.FIELDS_D).getAsJsonObject().get(Config.FIELDS_D_DEVS).getAsString();
		String md5_devs = getMd5(raw_devs);
		String raw_devs_without_time = getDevsWithoutTime(raw_devs);
		
		return result;
	}

	private String getDevsWithoutTime(String raw_devs) {
		String result = "";
		//使用正则把所有匹配到的时间，替换成为空。
		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}";
		result = raw_devs.replaceAll(regex, Config.SEPERATOR_NULL);
		
		return result;
	}

	private String getMd5(String raw_devs) {
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
			logger.error(e.getMessage());
		}
		
		return result;
	}

	public String getEvent_id(JsonElement root) {
		String result = "";
		if (root.getAsJsonObject().get(Config.FIELDS_IMSI1) != null) {
			result = root.getAsJsonObject().get(Config.FIELDS__ID).getAsString();
		} else {
			result = root.getAsJsonObject().get(Config.FIELDS_A).getAsString();
		}
		return result;
	}

	public String getTime_stamp1(JsonElement root) {
		return null;
	}

	public String getDid(JsonElement root) {
		return null;
	}

	public String getSv(JsonElement root) {
		return null;
	}

	public String getTag(JsonElement root) {
		return null;
	}

	public String getTime_stamp2(JsonElement root) {
		return null;
	}

	public String getImei1(JsonElement root) {
		return null;
	}

	public String getPkgn(JsonElement root) {
		return null;
	}

}
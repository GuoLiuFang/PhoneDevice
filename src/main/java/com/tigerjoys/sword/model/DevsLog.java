package com.tigerjoys.sword.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.google.gson.JsonElement;
import com.tigerjoys.sword.common.base.CommonKits;
import com.tigerjoys.sword.config.DevsConfig;

public class DevsLog {
	private CommonKits common_kits = null;

	public DevsLog() {
		this.common_kits = new CommonKits();
	}

	public String getApil(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_APIL)
				.getAsString();
		return result;
	}

	public String getUuid(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_UUID)
				.getAsString();
		return result;
	}

	public String getImsi1(JsonElement root) {
		String result = "";
		if (root.getAsJsonObject().get(DevsConfig.FIELDS_IMSI1) != null) {
			result = root.getAsJsonObject().get(DevsConfig.FIELDS_IMSI1)
					.getAsString();
		}
		return result;
	}

	public String getTime_stamp(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_TIME_STAMP)
				.getAsString();
		return result;
	}

	public String getMem_total(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_MEM_TOTAL)
				.getAsString();
		return result;
	}

	public String getData_free_space(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_DATA_FREE_SPACE)
				.getAsString();
		return result;
	}

	public String getMem_free(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_MEM_FREE)
				.getAsString();
		return result;
	}

	public String getSystem_free_space(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject()
				.get(DevsConfig.FIELDS_SYSTEM_FREE_SPACE).getAsString();
		return result;
	}

	public String getLinux_v(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_LINUX_V)
				.getAsString();
		// result = result.replaceAll(Config.REGEX_ENTER,
		// Config.SEPERATOR_NULL);
		return result;
	}

	public String get_id(JsonElement root) {
		String result = "";
		if (root.getAsJsonObject().get(DevsConfig.FIELDS__ID) != null) {
			result = root.getAsJsonObject().get(DevsConfig.FIELDS__ID)
					.getAsString();
		} else {
			result = root.getAsJsonObject().get(DevsConfig.FIELDS_A)
					.getAsString();
		}
		return result;
	}

	public String getDevs_md5(JsonElement root) {
		String result = "";
		String raw_devs = "";
		StringBuffer sb = new StringBuffer();
		if (root.getAsJsonObject().get(DevsConfig.FIELDS_D) != null) {
			raw_devs = root.getAsJsonObject().get(DevsConfig.FIELDS_D)
					.getAsJsonObject().get(DevsConfig.FIELDS_D_REASONS_DEVS)
					.getAsString();
		} else {
			raw_devs = root.getAsJsonObject().get(DevsConfig.FIELDS_REASONS)
					.getAsJsonObject().get(DevsConfig.FIELDS_D_REASONS_DEVS)
					.getAsString();
		}
		String md5_devs = common_kits.getMd5(raw_devs);
		String raw_devs_without_time = common_kits.getDevsWithoutTime(raw_devs);
		sb.append(md5_devs).append(DevsConfig.SEPERATOR_VERTICAL_BAR)
				.append(raw_devs).append(DevsConfig.SEPERATOR_VERTICAL_BAR)
				.append(raw_devs_without_time);
		result = sb.toString();
		return result;
	}

	public String getEvent_id(JsonElement root) {
		String result = "";
		if (root.getAsJsonObject().get(DevsConfig.FIELDS_EVENT_ID) != null) {
			result = root.getAsJsonObject().get(DevsConfig.FIELDS_EVENT_ID)
					.getAsString();
		} else {
			result = root.getAsJsonObject().get(DevsConfig.FIELDS_C)
					.getAsString();
		}

		return result;
	}

	public String getTime_stamp1(JsonElement root) {
		String result = "";
		if (root.getAsJsonObject().get(DevsConfig.FIELDS_TIME_STAMP) != null) {
			result = root.getAsJsonObject().get(DevsConfig.FIELDS_TIME_STAMP)
					.getAsString();
		} else {
			result = root.getAsJsonObject().get(DevsConfig.FIELDS_B)
					.getAsString();
		}
		result = convertMillisecondsToDateTime(result);
		return result;
	}

	private String convertMillisecondsToDateTime(String millisenconds) {
		String result = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.parseLong(millisenconds));
		SimpleDateFormat sdf = new SimpleDateFormat(
				DevsConfig.REGEX_FORMAT_DATETIME);
		result = sdf.format(calendar.getTime());
		return result;
	}

	public String getDid(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_DID)
				.getAsString();
		return result;
	}

	public String getSv(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_SV).getAsString();
		return result;
	}

	public String getTag(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_TAG)
				.getAsString();
		return result;
	}

	public String getImei1(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_IMEI1)
				.getAsString();
		return result;
	}

	public String getPkgn(JsonElement root) {
		String result = "";
		result = root.getAsJsonObject().get(DevsConfig.FIELDS_PKGN)
				.getAsString();
		return result;
	}

}

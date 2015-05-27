package com.tigerjoys.sword.model;

import com.google.gson.JsonArray;
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
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_APIL);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getUuid(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_UUID);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getImsi1(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_IMSI1);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getMem_total(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_MEM_TOTAL);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getData_free_space(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_DATA_FREE_SPACE);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getMem_free(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_MEM_FREE);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getSystem_free_space(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_SYSTEM_FREE_SPACE);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getLinux_v(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_LINUX_V);
		if (obj != null) {
			result = obj.getAsString();
			result = result.replaceAll(DevsConfig.REGEX_ENTER, DevsConfig.SEPERATOR_NULL);
		}
		return result;
	}

	public String get_id(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS__ID);
		if ( obj != null) {
			result = obj.getAsString();
		} else {
			obj = root.getAsJsonObject().get(DevsConfig.FIELDS_A);
			if (obj != null) {
				result = obj.getAsString();
			}
		}
		return result;
	}

	public String getDevs_md5(JsonElement root) {
		String result = "";
		String raw_devs = "";
		StringBuffer sb = new StringBuffer();
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_D);
		if (obj != null) {
			obj = obj.getAsJsonObject().get(DevsConfig.FIELDS_D_REASONS_DEVS);
			if (obj != null) {
				raw_devs = obj.getAsString();
			}
		} else {
			obj = root.getAsJsonObject().get(DevsConfig.FIELDS_REASONS);
			if (obj != null) {
				obj = obj.getAsJsonObject().get(DevsConfig.FIELDS_D_REASONS_DEVS);
				if (obj != null) {
					raw_devs = obj.getAsString();
				}
			}
		}
		raw_devs = common_kits.formatDevsString(raw_devs);
		String md5_devs = common_kits.getMd5(raw_devs);
//		String raw_devs_without_time = common_kits.getDevsWithoutTime(raw_devs);
		sb.append(md5_devs).append(DevsConfig.SEPERATOR_VERTICAL_BAR).append(raw_devs);
		result = sb.toString();
		return result;
	}
	
	public String getDevs_array_md5(JsonElement root) {
		String result = "";
		String raw_devs = "";
		StringBuffer sb = new StringBuffer();
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_DEVS_AARAY);
		if (obj != null) {
			JsonArray devs_array = obj.getAsJsonArray();
			StringBuffer sb_line = new StringBuffer();
			String permission = "";
			String name = "";
			String group = "";
			String owner = "";
			String time = "";
			for (int i = 0; i < devs_array.size(); i++) {
				permission = devs_array.get(i).getAsJsonObject().get(DevsConfig.FIELDS_DEVS_AARAY_PERMISSION).getAsString();
				name = devs_array.get(i).getAsJsonObject().get(DevsConfig.FIELDS_DEVS_AARAY_NAME).getAsString();
				group = devs_array.get(i).getAsJsonObject().get(DevsConfig.FIELDS_DEVS_AARAY_GROUP).getAsString();
				owner = devs_array.get(i).getAsJsonObject().get(DevsConfig.FIELDS_DEVS_AARAY_OWNER).getAsString();
				time = devs_array.get(i).getAsJsonObject().get(DevsConfig.FIELDS_DEVS_AARAY_TIME).getAsString();
				sb_line.append(permission).append(DevsConfig.SEPERATOR_COMMA).append(owner).append(DevsConfig.SEPERATOR_COMMA).append(group).append(DevsConfig.SEPERATOR_COMMA).append(time).append(DevsConfig.SEPERATOR_COMMA).append(name).append(DevsConfig.REGEX_ENTER);
			}
			raw_devs = sb_line.toString();
		}
		String md5_devs = common_kits.getMd5(raw_devs);
		sb.append(md5_devs).append(DevsConfig.SEPERATOR_VERTICAL_BAR).append(raw_devs);
		result = sb.toString();
		return result;
	}

	public String getEvent_id(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_EVENT_ID);
		if (obj != null) {
			result = obj.getAsString();
		} else {
			obj = root.getAsJsonObject().get(DevsConfig.FIELDS_C);
			if (obj != null) {
				result = obj.getAsString();
			}
		}
		return result;
	}

	public String getDid(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_DID);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getSv(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_SV);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getTag(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_TAG);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getImei1(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_IMEI1);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getPkgn(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_PKGN);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}
	
	public String getModel(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_MODEL);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}
	
	public String getBrand(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_BRAND);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

	public String getAppid(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_APPID);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}
	public String getSucess(JsonElement root) {
		String result = "";
		JsonElement obj = root.getAsJsonObject().get(DevsConfig.FIELDS_SUCESS);
		if (obj != null) {
			result = obj.getAsString();
		}
		return result;
	}

}
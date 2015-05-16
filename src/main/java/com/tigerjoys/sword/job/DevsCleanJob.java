package com.tigerjoys.sword.job;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import com.tigerjoys.sword.cleaner.DevsCleaner;
import com.tigerjoys.sword.config.DevsConfig;

public class DevsCleanJob {

	private static Logger logger = LogManager.getLogger();
	private static DevsCleaner devs_cleaner = new DevsCleaner();

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("devs_clean").setMaster(
				"local");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> raw_lines = sc.textFile(DevsConfig.PATH_RAW_DATA);
		JavaRDD<String> formatted_lines = raw_lines
				.map(new Function<String, String>() {
					public String call(String raw_line) {
						String result = "";
						if (raw_line != null) {
							if (StringUtils.isNotEmpty(raw_line.trim())) {
								result = devs_cleaner.clean(raw_line.trim());
							} else {
								logger.error("日志中这行数据为空" + raw_line);
							}
						} else {
							logger.error("行日志对象为null" + raw_line);
						}
						return result;
					}
				});

		formatted_lines.map(new Function<String, String>() {
			public String call(String formatted_line) {
				String result = "";
				String[] database_datafile = formatted_line
						.split(DevsConfig.SEPERATOR_VERTICAL_BAR);
				result = devs_cleaner.chooseDataBaseFields(database_datafile);
				return result;
			}
		}).saveAsTextFile(DevsConfig.PATH_DATA_BASE);

		formatted_lines.map(new Function<String, String>() {

			public String call(String formatted_line) {
				String result = "";
				String[] database_datafile = formatted_line
						.split(DevsConfig.SEPERATOR_VERTICAL_BAR);
				result = devs_cleaner.chooseDataFileFields(database_datafile);
				return result;
			}
		}).saveAsTextFile(DevsConfig.PATH_DATA_FILE);

	}
}
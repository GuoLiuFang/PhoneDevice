package com.tigerjoys.sword.job;


import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import com.tigerjoys.sword.cleaner.Cleaner;
import com.tigerjoys.sword.config.Config;

public class CleanJob {

	private static Logger logger = LogManager.getLogger();
	private static Cleaner cleaner = new Cleaner();

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("devs_clean").setMaster("local");

		JavaSparkContext sc = new JavaSparkContext(conf);

		JavaRDD<String> raw_lines = sc.textFile(Config.PATH_RAW_DATA);

		JavaRDD<String> formatted_lines = raw_lines.map(new Function<String, String>() {

			public String call(String raw_line) throws Exception {

				String result = "";
				if (raw_line != null) {

					if (StringUtils.isNotEmpty(raw_line.trim())) {

						result = cleaner.clean(raw_line.trim());

					} else {

						logger.error("日志中这行数据为空" + raw_line);

					}
				} else {
					logger.error("行日志对象为null" + raw_line);
				}// line != null

		return result;
	}
});//产出是：result;result中存放的是：所有devs日志的字段。没错是所有。然后在以map的形式拆分，分流到data_base和data_file中去。

		formatted_lines.map(new Function<String, String>() {

			public String call(String formatted_line) throws Exception {
				String result = "";
				String[] database_datafile = formatted_line.split(Config.SEPERATOR_VERTICAL_BAR);
				result = cleaner.chooseDataBaseFields(database_datafile);
				return result;
			}

		}).saveAsTextFile(Config.PATH_DATA_BASE);

		formatted_lines.map(new Function<String, String>() {

			public String call(String formatted_line) throws Exception {
				String result = "";
				String[] database_datafile = formatted_line.split(Config.SEPERATOR_VERTICAL_BAR);
				result = cleaner.chooseDataFileFields(database_datafile);
				return result;
			}

		}).saveAsTextFile(Config.PATH_DATA_FILE);

	}// main
}

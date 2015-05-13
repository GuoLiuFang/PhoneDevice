package com.tigerjoys.sword.job;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;


import com.tigerjoys.sword.cleaner.DevsCleaner;
import com.tigerjoys.sword.config.Config;

public class SwordCleanJob {
	
	private static Logger logger = LogManager.getLogger();
	private static DevsCleaner devs_cleaner = new DevsCleaner();

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setAppName("sword-devs-clean").setMaster("local");
		
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		JavaRDD<String> lines = sc.textFile(Config.RAW_DATA_PATH);
		
		JavaRDD<String> lines_devices = lines.map(new Function<String, String>() {
			
			public String call(String line) throws Exception {
				
				String result = "";
				if (line != null) {
					
					if (StringUtils.isNotEmpty(line.trim())) {
						
						result = devs_cleaner.clean(line.trim());
						
					} else {
						
						logger.error("日志中这行数据为空" + line);
						
					}
				}else {
					logger.error("行日志对象为null" + line);
				}//line != null
				
				return result;
			}
		});
		
		lines_devices.map(new Function<String, String>() {

			public String call(String line_disposed) throws Exception {
				String[] database_datafileStrings = line_disposed.split("");
				return database_datafileStrings[Config.DATA_BASE_INDEX];
			}
			
		}).saveAsTextFile("");
		
		
		
		//data_file中存放的是md5，devs。
		lines_devices.map(new Function<String, String>() {

			public String call(String line_disposed) throws Exception {
				String[] database_datafileStrings = line_disposed.split("");
				return database_datafileStrings[Config.DATA_FILE_INDEX];
			}
			
		}).saveAsTextFile("");
		
		

	}// main
}

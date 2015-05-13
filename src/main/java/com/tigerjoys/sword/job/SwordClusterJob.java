//package com.tigerjoys.sword.job;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.util.HashSet;
//import java.util.Iterator;
//
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.JavaPairRDD;
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.api.java.function.Function;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.api.java.function.VoidFunction;
//
//import scala.Tuple2;
//import scala.Tuple5;
//
//import com.tigerjoys.sword.cleaner.DevsCleaner;
//import com.tigerjoys.sword.module.Device;
//
//public class SwordClusterJob {
//	private static Logger logger = LogManager.getLogger();
//	private static HashSet<String> DevsSet = new HashSet<String>();
//
//	public static void main(String[] args) {
//
//		SparkConf conf = new SparkConf().setAppName("sword-classification-clustering").setMaster("local");
//		JavaSparkContext sc = new JavaSparkContext(conf);
//		JavaRDD<String> lines = sc.textFile("data.txt");
//		DevsCleaner devs_cleaner = new DevsCleaner();
//
//		// 这个函数完成两个任务，一个是清洗数据入库；一个是返回一个devs的数据集。
//		JavaRDD<String> devices = lines.map(new Function<String, String>() {
//			public Device call(String line) throws Exception {
//				Device device = null;
//				if (StringUtils.isNotEmpty(line)) {
//					device = devs_cleaner.getDevice(line);
//				} else {
//					logger.error("这行数据为空" + line);
//					device = devs_cleaner.getNullDevice();
//				}
//				return device;
//			}
//		});
//		devices.map(new Function<Device, String>() {
//			public String call(Device device) throws Exception {
//				return null;
//			}
//		}).saveAsTextFile("");
//
//		JavaPairRDD<String, String> linuxDevs = devices
//				.mapToPair(new PairFunction<Device, String, String>() {
//					public Tuple2<String, String> call(Device arg0)
//							throws Exception {
//						return null;
//					}
//				});
//		if (!DevsSet.isEmpty()) {
//			DevsSet.clear();
//		}
//		linuxDevs.foreach(new VoidFunction<Tuple2<String, String>>() {
//			public void call(Tuple2<String, String> linux_devs)
//					throws Exception {
//				DevsSet.add(linux_devs._2);
//			}
//		});
//
//		JavaPairRDD<String, Iterable<String>> linuxDevsN = linuxDevs
//				.groupByKey();
//		linuxDevsN
//				.map(new Function<Tuple2<String, Iterable<String>>, Tuple5<Integer, Float, Float, String, String>>() {
//					public Tuple5<Integer, Float, Float, String, String> call(
//							Tuple2<String, Iterable<String>> linux_devs_n)
//							throws Exception {
//						String linux_version = linux_devs_n._1;
//						Iterator<String> devsIt = linux_devs_n._2.iterator();
//						HashSet<String> dev_set = new HashSet<String>();
//						if (!dev_set.isEmpty()) {
//							dev_set.clear();
//						}
//						while (devsIt.hasNext()) {
//							dev_set.add(devsIt.next());
//						}
//						int count = dev_set.size();
//
//						return new Tuple5(linux_version, count, 0, 0, dev_set);
//					}
//				});
//
//	}// main
//}

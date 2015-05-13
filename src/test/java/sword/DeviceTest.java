package sword;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import com.tigerjoys.sword.cleaner.Cleaner;
import com.tigerjoys.sword.config.Config;

public class DeviceTest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
//
	@Test
	public void testSplitLine() {
		Scanner stdin = new Scanner(System.in);
		System.out.println("请输入数据：");
		String line = stdin.nextLine();
		Cleaner devCleaner = new Cleaner();
//		int type = devCleaner.getLogType(line);
//		System.out.print(type);
		
//		
	}
	@Test
	public void testConfig(){
		
		
		
		System.out.println("结果是");
		System.out.println(Config.COMMA_SEPERATOR);
	}

}

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
		Cleaner cleaner = new Cleaner();
		String result = cleaner.clean(line);
		System.out.print(result);
		
//		
	}
	@Test
	public void testConfig(){
		
		
		
		System.out.println("结果是");
		for (String  re : Config.TYPE_ALIAS_ARRAY) {
			
			System.out.println(re);
		}
	}

}

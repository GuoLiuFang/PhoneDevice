package sword;

import java.util.Scanner;

import org.junit.Test;

import com.tigerjoys.sword.cleaner.DevsCleaner;
import com.tigerjoys.sword.common.base.CommonKits;

public class DeviceTest {

	@Test
	public void testSplitLine() {
		Scanner stdin = new Scanner(System.in);
		System.out.println("请输入数据：");
		String line = stdin.nextLine();
		DevsCleaner cleaner = new DevsCleaner();
		String result = cleaner.clean(line);
		System.out.print(result);
	}
	
	
	@Test
	public void testAny() {
		CommonKits common_kits = new CommonKits();
		String line = "";
		String result = common_kits.formatDevsString(line);
		System.out.print(result);
	}

}
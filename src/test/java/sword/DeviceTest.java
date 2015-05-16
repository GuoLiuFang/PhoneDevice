package sword;

import java.util.Scanner;
import org.junit.Test;
import com.tigerjoys.sword.cleaner.DevsCleaner;

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

}
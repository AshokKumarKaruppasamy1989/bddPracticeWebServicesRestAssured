package resuable;

import java.io.FileInputStream;
import java.util.Properties;


public class ReadPropertyFile {
	
	public static Properties prop;
	FileInputStream file;

	public void propReader() {
		String path = System.getProperty("user.dir") + "//src/test/resources//configfiles//input.properties";
		try {
			prop = new Properties();
			file = new FileInputStream(path);
			prop.load(file);
		} catch (Exception e) {
			System.out.println("File is not present");
		}
	}

}

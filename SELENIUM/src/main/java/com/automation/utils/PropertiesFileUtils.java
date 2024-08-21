package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtils {
	private static Properties properties;

	public static String getProperty(String key) throws IOException {
		FileInputStream file = new FileInputStream("./configuration/configs.properties");
		properties = new Properties();
		properties.load(file);
		return properties.getProperty(key);
	}
}
	

package com.api.auto.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesFileUtils {
	private static String CONFIG_PATH = "./configuration/configs.properties";
	private static String TOKEN_PATH = "./configuration/token.properties";
	
	public static String getProperty(String key) {
		Properties properties = new Properties();
		String value = null;
		FileInputStream fileinputstream = null;
		try {
			fileinputstream = new FileInputStream(CONFIG_PATH);
				properties.load(fileinputstream);
				value = properties.getProperty(key);
		} catch (Exception ex) {
			System.out.println("Xảy ra lỗi đọc giá trị của key" + key);
			ex.printStackTrace();
			throw new RuntimeException("Không thể đọc giá trị của key: " + key, ex);
	    } finally {
			if (fileinputstream != null) {
				try {
					fileinputstream.close();
				} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
		return value;
	}

	public static void saveToken(String token) {
		Properties properties = new Properties();
		FileOutputStream fileoutputstream = null;
		try {
			fileoutputstream = new FileOutputStream(TOKEN_PATH);
			properties.setProperty("token",token);
			properties.store(fileoutputstream, null);
		} catch (Exception ex) {
			System.out.println("Xảy ra lỗi đọc giá trị của token");
			ex.printStackTrace();
		} finally {
			if (fileoutputstream !=null) {
				try {
					fileoutputstream.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public static String getToken(String token) {
		Properties properties = new Properties();
		String value = null;
		FileInputStream	fileinputstream = null;
		try {
			fileinputstream = new FileInputStream(TOKEN_PATH);
		    properties.load(fileinputstream);
		    value = properties.getProperty(token);
		} catch (Exception ex) {
			System.out.println("Xảy ra lỗi khi đọc giá trị token");
			ex.printStackTrace();
		}finally {
			if (fileinputstream !=null) {
				try {
					fileinputstream.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		return value;
	}
}
package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	// to create object only once -made private and static
	private static Properties prop;

	// to read and load prop file only once - kept in static block
	// static block will executed once during class loading time
	static {
		prop = new Properties();
		File configFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties");
		FileReader fr;
		try {
			fr = new FileReader(configFile);
			prop.load(fr);
		} catch (FileNotFoundException e) {

			System.out.println("Issue with Reading File");
		} catch (IOException e) {

			System.out.println("Issue with loading File");
		}
	}

	// to load configmanager only once in method area- made static

	public static String getProperty(String key) {
		return prop.getProperty(key);

	}
	
	// to restrict user not to create object-made constructor private
	private ConfigManager()
	{
		
	}

}

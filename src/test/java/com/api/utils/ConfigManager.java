package com.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	// to create object only once -made private and static
	private static Properties prop;

	private static String path;

	private static String env;

	// to read and load prop file only once - kept in static block
	// static block will executed once during class loading time
	static {
		prop = new Properties();

		// env is passed it picks up if not passed then QA is picked up

		env = System.getProperty("env", "QA").trim().toUpperCase();

		switch (env) {
		case "DEV" -> path = "config" + File.separator + "config_DEV.properties";

		case "UAT" -> path = "config" + File.separator + "config_UAT.properties";

		case "QA" -> path = "config" + File.separator + "config_QA.properties";

		default -> path = "config" + File.separator + "config_QA.properties";

		}

		// to look for files in src/test/resources
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null)
			throw new RuntimeException("Cannot Find the file at the path" + path);
		try {

			prop.load(input);
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
	private ConfigManager() {

	}

}

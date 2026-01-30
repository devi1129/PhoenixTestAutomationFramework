package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {

	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> clazz) {

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

		ObjectMapper objectMapper = new ObjectMapper();
		List<T> list = null;
		T[] array;

		try {
			array = objectMapper.readValue(inputStream, clazz);

			list = Arrays.asList(array);

		} catch (IOException e) {

			e.printStackTrace();
		}

		return list.iterator();

	}

}

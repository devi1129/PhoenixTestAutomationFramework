package com.api.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.UserCredentials;
import com.dataproviders.api.Bean.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil {

	private ExcelReaderUtil() {

	}

	public static <T> Iterator<T> loadTestData(String sheetName,Class<T> clazz) {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData" + File.separator + "PhoenixTestData.xlsx");

		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {

			e.printStackTrace();
		}

		XSSFSheet mysheet = myWorkbook.getSheet(sheetName);

		List<T> dataList = Poiji.fromExcel(mysheet, clazz);
		
		return dataList.iterator();
	}

}

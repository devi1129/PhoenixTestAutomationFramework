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

public class ExcelReaderUtil {

	private ExcelReaderUtil() {

	}

	public static Iterator<UserCredentials> loadTestData()  {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData" + File.separator + "PhoenixTestData.xlsx");

		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
		
			e.printStackTrace();
		}

		XSSFSheet mysheet = myWorkbook.getSheet("LoginTestData");

		XSSFRow myRow;

		XSSFCell myCell;

		XSSFRow headerRows = mysheet.getRow(0);
		int userNameIndex = -1;
		int passwordIndex = -1;

		for (Cell cell : headerRows) {

			if (cell.getStringCellValue().trim().equalsIgnoreCase("username")) {
				userNameIndex = cell.getColumnIndex();
			}

			if (cell.getStringCellValue().trim().equalsIgnoreCase("password")) {
				passwordIndex = cell.getColumnIndex();
			}

		}
		UserCredentials userCredentials;
		int lastRowIndex = mysheet.getLastRowNum();
		XSSFRow rowData;

		List<UserCredentials> list = new ArrayList();
		for (int rowIndex = 1; rowIndex <= lastRowIndex; rowIndex++) {

			rowData = mysheet.getRow(rowIndex);

			userCredentials = new UserCredentials(rowData.getCell(userNameIndex).toString(),
					rowData.getCell(passwordIndex).toString());

			list.add(userCredentials);

		}

		return list.iterator();

	}

}

package com.dpr.utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataPicker {

	@DataProvider(name = "loginCredentials")
	public Object[][] loginCredentials(Method m) {

		if (m.getName().equalsIgnoreCase("loginValid")) {
			return new Object[][] {

					{ "igtsystems", "1gtsystems!" } };
		} else {
			return new Object[][] { 
				{ "igtsytems", "invalid" }, 
				{ "invalid", "1gtsystems!" }, 
				{ "", "1gtsystems!" },
				{ "igtsystems", "" }

			};
		}

	};
	
	/*@DataProvider(name = "loginCredentials")
	public Object[][] loginCredentials(){
		Object [][] testObjArray = ExcelUtils.getTableArray("", SheetName)
		return (testObjArray);
		
	}
	*/
}

package com.AmazonRetail.Helpers;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.AmazonRetail.GenericMethods.Constants;


//////////////////////////////////////////////////////////////////////////////////
public class ReadConfigProperty extends Constants {
	
	static Properties properties = null;
	
	public static String getConfigValues(String elementValue)
	{
		if (properties == null) {
			try {

				File file = new File(configpath + "\\config.properties");
				

				FileInputStream fileInput = new FileInputStream(file);
				properties = new Properties();
				properties.load(fileInput);

			} catch (Exception e) {

				//MainTestNG.LOGGER.severe("IOException"+e.getMessage());
			}
		}
		return properties.getProperty(elementValue);
	}


}

package utils;

import java.io.FileInputStream;

import java.util.Properties;

public class ConfigReader {
	
Properties properties;
	
	//String CONFIG_FILE_PATH = "/RestAssuredFramework/src/test/resources/config.properties";
	
	String CONFIG_FILE_PATH = ".\\src\\test\\resources\\config.properties";
	
	
	//FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
	
	public ConfigReader()
	{
		
		
		FileInputStream fileInputStream;
		try 
		{
			fileInputStream = new FileInputStream(CONFIG_FILE_PATH);
			properties = new Properties();
			properties.load(fileInputStream);
		} 
		
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Failed to load the file...");
		}
		
	}
	
	public String getProperty(String key)
	{
		return properties.getProperty(key);
	}
	
	public int getIntProperty(String key)
	{
		return Integer.parseInt(properties.getProperty(key));
	}

}

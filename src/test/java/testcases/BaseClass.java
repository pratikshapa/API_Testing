package testcases;

import java.util.List;


import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import routes.Routes;
import utils.ConfigReader;

public class BaseClass {
	
	ConfigReader configreader ;
	//public static final String BASE_URL = "https://fakestoreapi.com/";

	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI = Routes.BASE_URL; // By default use Base URL in all the test cases 
		
		configreader = new ConfigReader();
	}
	
	//This method will check whether order is descending or not
	
	boolean isSortedDescending(List<Integer> list)
	{
		for(int i=0;i<list.size()-1;i++)
		{
			if(list.get(i)<list.get(i+1))
			{
				return false; //
			}
		}
		
		return true;
	}
	
	//This method will check whether order is ascending or not
	
	boolean isSortedAscending(List<Integer> list)
	{
		for(int i=0;i<list.size()-1;i++)
		{
			if(list.get(i)>list.get(i+1))
			{
				return false; //
			}
		}
		
		return true;
	}
}





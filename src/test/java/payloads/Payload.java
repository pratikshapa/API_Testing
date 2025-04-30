package payloads;

import java.util.Random;




import com.github.javafaker.Faker;

import pojo.Product;

public class Payload {
	
Faker faker = new Faker();
	
	Random random =new Random();

	
	Product product; 
	
	String str =  "electronics";
	
	String catagories[] = {"electronics", "furniture", "clothing" , "beauty", "books"};

	public Product productPayload()
	{
		String title = faker.commerce().productName();
		
		double price = Double.parseDouble(faker.commerce().price());
		
		String desription = faker.lorem().sentence();
		
		String catagory = catagories[random.nextInt(catagories.length)];
		
		String image = "";
		
		return new Product(title,price,desription,catagory,image);
		
		
	}


	//Payload for cart
	
	//Payload for User

	//Payload for Login
	

}

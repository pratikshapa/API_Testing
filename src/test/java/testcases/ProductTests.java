package testcases;

import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import routes.Routes;
import utils.ConfigReader;

public class ProductTests extends BaseClass {
	
	Payload payload = new Payload();
	ConfigReader configReader = new ConfigReader();

	// Fetch all product details.
	//@Test
	public void testGetAllProducts()
	{
		given()
		
		.when()
		    .get(Routes.GET_ALL_PRODUCTS)
		
		.then()
		    .statusCode(200)
		    .body("size()",greaterThan(0))
		    .log().all();
	}
	
	// Fetch product by ID
	
		//@Test
		public void testGetSingleProductById()
		{
			int productId = configreader.getIntProperty("productId");
			
			given()
			    .pathParam("id",productId)
			
			.when()
			    .get(Routes.GET_PRODUCT_BY_ID)
			
			.then()
			    .statusCode(200)
			    .log().all();
		}
		
		// Get Product with Limit
	//	@Test
		public void testGetProductWithLimit()
		{
			
			given()
			    .pathParam("limit", 5)
			   
			
			.when()
			    .get(Routes.GET_PRODUCT_WITH_LIMIT)
			
			.then()
			    .statusCode(200)
			    .body("size()",equalTo(5))
			    .log().all();
		}
		
		// Sort product descending / ascending order
		//@Test
		public void testGetSortedProducts()
		{	
			Response response = given()
			    .pathParam("order", "desc")
			
			.when()
			    .get(Routes.GET_PRODUCT_BY_SORT)
			
			.then()
			    .statusCode(200)
			    .extract().response();
			
			List<Integer> productIds = response.jsonPath().getList("id", Integer.class);

			boolean status = isSortedDescending(productIds);
			
			//boolean status = isSortedAscending(productIds);
			
			assertEquals(status, true);
			//assertEquals(status, false);
		
		}
		
		// Get all categories
				//@Test
				public void testGetAllCategories()
				{	
					given()
					
					.when()
					    .get(Routes.GET_ALL_CATAGORIES)
					
					.then()
					   .statusCode(200)
					   .body("size()", greaterThan(0))
					   .log().all();
				
				}
				
				// To get products by category
				//@Test
				public void testProductBCategory()
				{	
					given()
					   .pathParam("category", "electronics")
					
					.when()
					    .get(Routes.GET_PRODUCT_WITH_CATAGORIES)
					
					.then()
					   .statusCode(200)
					   .body("size()", greaterThan(0))
					   .body("category", everyItem(equalTo("electronics")))
					   .log().all();
				
				}
				
				// Add new Product
				@Test
				public void testAddNewProduct()
				{	
					pojo.Product newProduct = payload.productPayload();
					
					int productId = given()
					  .body(newProduct)
					  .contentType(ContentType.JSON)
					
					.when()
					    .post(Routes.PRODUCT_CREATE)
					
					.then()
					   .statusCode(200)
					   .body("id", notNullValue())
					   .body("title", equalTo(newProduct.getTitle()))
					   .extract().jsonPath().getInt("id");
					
					System.out.println(productId);
				
				}
				
				// Update Product
				//@Test
				public void testUpdateProduct()
				{	
					int productId = configReader.getIntProperty("productId");
					pojo.Product newUpdatedProduct = payload.productPayload();
					
					given()
					    .contentType(ContentType.JSON)
					    .pathParam("id",productId)
					    .body(newUpdatedProduct)
					
					.when()
					    .put(Routes.PRODUCT_UPDATE)
					
					.then()
					   .statusCode(200)
					   .log().body();
					  
				}
				
				// Delete Product

				@Test
				public void testDeleteProduct()
				{	
					int productId = configReader.getIntProperty("productId");
							
					given()
					   .contentType(ContentType.JSON)
					   .pathParam("id",productId)
						
								
					.when()
					   .delete(Routes.PRODUCT_DELETE)
								
					.then()
					   .statusCode(200);
								  
				}		

}

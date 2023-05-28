package restAssuredReference;

import static io.restassured.RestAssured.given;

//import org.testng.Assert;

import io.restassured.RestAssured;

public class DeleteReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// step 1:declare base url
				RestAssured.baseURI="https://reqres.in/";
				
				// : configure request body
				//without log all
			int statusCode =given().header("Content-Type","application/json").when().delete("/api/users/2").then().extract().statusCode();
			System.out.println(statusCode);
			
			// : validate the responseBody parameter
			//Assert.assertEquals(statusCode,201);
			
	}

}

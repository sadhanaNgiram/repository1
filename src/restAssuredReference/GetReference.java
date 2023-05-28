package restAssuredReference;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;

public class GetReference {

	public static void main(String[] args) {
		// step 1:declare base url
		RestAssured.baseURI="https://reqres.in/";
		
		// : configure request body
		//without log all
	int statusCode =given().header("Content-Type","application/json").when().get("/users?page=2").then().extract().statusCode();
	//System.out.println(statusCode);
	
	// : validate the responseBody parameter
		Assert.assertEquals(statusCode,201);
		
	}

}

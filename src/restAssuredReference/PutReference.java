package restAssuredReference;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PutReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// step 1:declare base url
				RestAssured.baseURI="https://reqres.in/";
				
				//step 2 : configure request body
				//without log all
			int statusCode =given().header("Content-Type","application/json").body("{\r\n"
						+ "    \"name\": \"morpheus\",\r\n"
						+ "    \"job\": \"zion resident\"\r\n"
						+ "}").when().put("/api/users/2").then().extract().statusCode();
			//System.out.println(statusCode);
			

			//using log all
			String responseBody =given().header("Content-Type","application/json").body("{\r\n"
					+ "    \"name\": \"morpheus\",\r\n"
					+ "    \"job\": \"zion resident\"\r\n"
					+ "}").log().all().when().put("/api/users/2").then().log().all().extract().response().asString();
			System.out.println(responseBody);

			//without log all
		/*	String responseBody =given().header("Content-Type","application/json").body("{\r\n"
					+ "    \"name\": \"morpheus\",\r\n"
					+ "    \"job\": \"zion resident\"\r\n"
					+ "}").when().put("/api/users/2").then().extract().response().asString();*/
			
			//step 3 : Parse the response Body
			JsonPath jsp = new JsonPath(responseBody);
			String res_name=jsp.getString("name");
			String res_job=jsp.getString("job");
			//String res_id=jsp.getString("id");
			//String res_createdAt=jsp.getString("createdAt");
			
			//step 4 : validate the responseBody parameter
			Assert.assertEquals(statusCode,200);
			
			Assert.assertEquals(res_name,"morpheus");
			Assert.assertEquals(res_job,"zion resident");
			


	}

}

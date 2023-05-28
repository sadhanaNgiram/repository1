package restAssuredReference;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.sql.Date;
import java.time.LocalDateTime;

import org.testng.Assert;


public class PostReference3 {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//declare base uri and request body variables
		String BaseURI = "https://reqres.in/";
		String RequestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		//Fetch RequestBody parameters values.
		
		JsonPath jsprequest=new JsonPath(RequestBody);
		String req_name =jsprequest.getString("name");
		String req_job=jsprequest.getString("job");
		//generate date in format as shown and received in response
		LocalDateTime Date=LocalDateTime.now();
		String exp_date=Date.toString().substring(0,11);
		
		RestAssured.baseURI= BaseURI;
		int statusCode=given().header("Content-Type","application/json").body(RequestBody).when().post("/api/users").then().extract().statusCode();
		String responseBody=given().header("Content-Type","application/json").body(RequestBody).when().post("/api/users").then().extract().response().asString();

		//parse	the response body
		JsonPath jsp=new JsonPath(responseBody);
		String res_name=jsp.getString("name");
		String res_job=jsp.getString("job");
		String res_id=jsp.getString("id");
		String res_createdAt=jsp.getString("createdAt");
		res_createdAt=res_createdAt.substring(0,11);

		//validate response body 
		Assert.assertEquals(res_name,req_name);	
		Assert.assertEquals(res_job,req_job);
		Assert.assertNotNull(res_id);
		Assert.assertEquals(res_createdAt, exp_date);
	
	}

}

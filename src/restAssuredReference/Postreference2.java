package restAssuredReference;
import io.restassured.path.json.JsonPath;
import java.time.LocalDateTime;
import io.restassured.RestAssured;
import org.testng.Assert;
import static io.restassured.RestAssured.given;
public class Postreference2 {

	public static void main(String[] args) {
//declare base Url
		String  baseurl="https://reqres.in/";
		RestAssured.baseURI="baseurl";
		
		//save requestbody in local variable
		String requestBody="{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		//Extract RequestBody parameters
	JsonPath jspRequest = new JsonPath(requestBody);
	String req_name=jspRequest.getString("name");
	String req_job=jspRequest.getString("job");
	
	//set Expected date
	String expDate=LocalDateTime.now().toString().substring(0,10);
	
	//configure request body
	int statusCode=given().header("Content-Type","application/json").body(requestBody).when().get("/api/users").then().extract().statusCode();
	String responseBody = given().header("Content-Type","application/json").body(requestBody).when().get("/api/users").then().extract().response().asString();
	 
	//parse the response body
	JsonPath jsp= new JsonPath(responseBody);
	String res_name=jsp.getString("name");
	String res_job=jsp.getString("job");
	String res_id=jsp.getString("id");
	String res_createdAt=expDate.substring(0,10);
	
	//validate the responsebody parameter
	Assert.assertEquals(statusCode,200);
	Assert.assertEquals(res_name,req_name);
	Assert.assertEquals(res_job,req_job);
	Assert.assertNotNull(res_id);
	Assert.assertEquals(res_createdAt,expDate);
	
	}

}

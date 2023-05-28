package restAssuredReference;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
public class PostReference {

	public static void main(String[] args) {
		// step 1:declare base url
		RestAssured.baseURI="https://reqres.in/";
		
		//step 2 : configure request body
		//without log all
	int statusCode =given().header("Content-Type","application/json").body("{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}").when().post("/api/users").then().extract().statusCode();
	//System.out.println(statusCode);
	

	//using log all
	String responseBody =given().header("Content-Type","application/json").body("{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"leader\",\r\n"
			+ "    \"id\": \"382\",\r\n"
			+ "    \"createdAt\": \"2023-05-14T08:58:53.801Z\"\r\n"
			+ "}\r\n"
			+ "").log().all().when().post("/api/users").then().log().all().extract().response().asString();
	System.out.println(responseBody);

	//without log all
/*	String responseBody =given().header("Content-Type","application/json").body("{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"leader\"\r\n"
			+ "}").when().post("/api/users").then().extract().response().asString();*/
	
	//step 3 : Parse the response Body
	JsonPath jsp = new JsonPath(responseBody);
	String res_name=jsp.getString("name");
	String res_job=jsp.getString("job");
	String res_id=jsp.getString("id");
	String res_createdAt=jsp.getString("createdAt");
	
	//step 4 : validate the responseBody parameter
	Assert.assertEquals(statusCode,201);
	
	Assert.assertEquals(res_name,"morpheus");
	Assert.assertEquals(res_job,"leader");
	
	
	}
	
}
	

	
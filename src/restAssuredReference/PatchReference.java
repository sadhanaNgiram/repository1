package restAssuredReference;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class PatchReference {

	public static void main(String[] args) {
		
		// step 1:declare base url
		RestAssured.baseURI="https://reqres.in/";
		
	//using log all
	String responseBody =given().header("Content-Type","application/json").body("{\r\n"
			+ "    \"name\": \"morpheus\",\r\n"
			+ "    \"job\": \"zion resident\"\r\n"
			+ "}").log().all().when().patch("/api/users/2").then().log().all().extract().response().asString();
	System.out.println(responseBody);

	//step 3 : Parse the response Body
	JsonPath jsp = new JsonPath(responseBody);
	String res_name=jsp.getString("name");
	String res_job=jsp.getString("job");
	
	Assert.assertEquals(res_name,"morpheus");
	Assert.assertEquals(res_job,"zion resident");
	
	}

}

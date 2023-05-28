package restAssuredReference;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.path.xml.*;
public class SoapReference {

	public static void main(String[] args) {
//step1: declare baseuri and requestbody variables
		String BaseURI="https://www.dataaccess.com";
		String requestBody="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n"
				+ " ";
		//step2 : fetch request parameter
		XmlPath xml_req=new XmlPath(requestBody);
		String result1 = xml_req.getString("ubiNum");
		System.out.println(result1);
 		
		//step3: configure API and fetch response body
		RestAssured.baseURI=BaseURI;
		String responseBody=given().header("Content-Type","text/xml; charset=utf-8").body(requestBody).when().post("/webservicesserver/NumberConversion.wso")
		.then().extract().response().getBody().asString();
		System.out.println(responseBody);
		
		//step4: parse the responsebody and fetch the responsebody parameter
		XmlPath xml_res=new XmlPath(responseBody);
		String result = xml_res.getString("NumberTowardsResult");
		System.out.println(result);
		
		//step5 :validate responsebody parameter
		Assert.assertEquals(result,"one hundred ");
		
	}

}

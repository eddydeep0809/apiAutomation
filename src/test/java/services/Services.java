package services;

import org.testng.Assert;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.util.HashMap;

public class Services {

	HashMap<String, String> Services	=	new HashMap<String, String>();

	public void getAllServices(String requestURL, String statusCode) {
				given()
				.when()
				.get(requestURL)
				.then()
				.statusCode(Integer.parseInt(statusCode))
				.extract().response();
	}


	public String createNewService(String requestURL, String serviceName, String statusCode) {
		Services.put("name", serviceName);
		Response res=
				given()
				.contentType("application/json")
				.body(Services)
				.when()
				.post(requestURL)
				.then()
				.statusCode(Integer.parseInt(statusCode))
				.extract().response();
		Assert.assertEquals(res.asString().contains("id"), true);
		String serviceID		=	res.jsonPath().getString("id");
		return serviceID;
	}

	public void getService(String requestURL, String serviceId, String serviceName, String statusCode) {
		Response res=
				given()
				.when()
				.get(requestURL+serviceId)
				.then()
				.statusCode(Integer.parseInt(statusCode))
				.extract().response();
		Assert.assertEquals(res.asString().contains(serviceId), true);
		Assert.assertEquals(res.asString().contains(serviceName), true);
	}

	public void updateService(String requestURL, String serviceId, String serviceName, String statusCode) {
		HashMap<String, String> updateService	=	new HashMap<String, String>();
		updateService.put("id", serviceId);
		updateService.put("name", serviceName);
		Response res =
				given()
				.contentType("application/json")
				.body(updateService)
				.when()
				.patch(requestURL+serviceId)
				.then()
				.statusCode(Integer.parseInt(statusCode))
				.extract().response();
		Assert.assertEquals(res.asString().contains(serviceName), true);
	}
	
	public void deleteService(String requestURL, String serviceId, String statusCode) {
		given()
		.when()
		.delete(requestURL+serviceId)
		.then()
		.statusCode(Integer.parseInt(statusCode));
		//.log().body();
	}
}

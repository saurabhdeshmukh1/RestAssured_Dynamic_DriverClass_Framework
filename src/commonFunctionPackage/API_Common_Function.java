package commonFunctionPackage;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class API_Common_Function {
	public static int response_statusCode(String baseURI , String resource , String requestBody) { 
		
		RestAssured.baseURI = baseURI;

		int statusCode = given().header("Content-Type" , "application/json").body(requestBody)
				         .when().post(resource)
				         .then().extract().statusCode();
 
		return statusCode;
	}

	public static String response_body(String baseURI , String resource , String requestBody) {
		
		RestAssured.baseURI = baseURI;

		String responseBody = given().header("Content-Type","application/json").body(requestBody)
				              .when().post(resource)
				              .then().extract().response().asString();

		return responseBody; 
	}

} 

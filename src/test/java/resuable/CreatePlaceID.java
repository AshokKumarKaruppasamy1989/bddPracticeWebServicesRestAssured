package resuable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreatePlaceID extends ReadPropertyFile {
	
	RequestSpecification request;
	Response response;
	public String placeID;
	SoftAssert soft = new SoftAssert();
	
	public void establishConnection() {
		ReadPropertyFile property = new ReadPropertyFile();
		property.propReader();
		request = RestAssured.given();
		request.baseUri(prop.getProperty("baseURI"));
	}
	
	public void placeIDCreate() {
		request.queryParam("Key", "qaclick123");
		request.header("Content-Type", "application/json");
		
		JSONObject directObject = new JSONObject();
		directObject.put("accuracy", prop.getProperty("accuracyVal"));
		directObject.put("name", prop.getProperty("nameVal"));
		directObject.put("phone_number", prop.getProperty("phone_number"));
		directObject.put("address", prop.getProperty("addressVal"));
		directObject.put("website", prop.getProperty("websiteVal"));
		directObject.put("language", prop.getProperty("languageVal"));
		
		JSONArray arr = new JSONArray();
		arr.add(prop.getProperty("typesArr1"));
		arr.add(prop.getProperty("typesArr2"));
		
		directObject.put("types", arr);
		
		JSONObject childObject = new JSONObject();
		childObject.put("lat", prop.getProperty("locationLat"));
		childObject.put("lng", prop.getProperty("locationLng"));
		
		directObject.put("location", childObject);
		request.body(directObject);
	}
	
	public void createRequestPost() {
		response = request.post("/maps/api/place/add/json");
		System.out.println(response.asString());
	}
	
	public void postResponseSuccessValidation() {
		Assert.assertTrue(response.getStatusCode() == 200);
		soft.assertTrue(response.jsonPath().get("status").equals("OK"));
		placeID = response.jsonPath().get("place_id");
		System.out.println(placeID);
		Assert.assertTrue(!placeID.isBlank());
	}
	
	public void createRequestGet() {
		establishConnection();
		request.queryParam("Key", "qaclick123");
		request.queryParam("place_id", placeID);		
		response = request.get("/maps/api/place/get/json");
		System.out.println(response.asString());
	}
	
	public void getResponseValidation() {
		soft.assertTrue(response.getStatusCode() == 200);
		soft.assertTrue(response.jsonPath().get("location.latitude").equals(prop.getProperty("locationLat")));
		soft.assertTrue(response.jsonPath().get("location.longitude").equals(prop.getProperty("locationLng")));
		soft.assertTrue(response.jsonPath().get("accuracy").equals(prop.getProperty("accuracyVal")));
		soft.assertTrue(response.jsonPath().get("name").equals(prop.getProperty("nameVal")));
		soft.assertTrue(response.jsonPath().get("phone_number").equals(prop.getProperty("phone_number")));
		soft.assertTrue(response.jsonPath().get("address").equals(prop.getProperty("addressVal")));
		soft.assertTrue(response.jsonPath().get("types").equals(prop.getProperty("typesArr1") + "," + prop.getProperty("typesArr2")));
		soft.assertTrue(response.jsonPath().get("website").equals(prop.getProperty("websiteVal")));
		soft.assertTrue(response.jsonPath().get("language").equals(prop.getProperty("languageVal")));
	}

}

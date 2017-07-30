package main;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class Utility {
public static final String WITHOUT_BODY = "0";
public static final String WITH_INVALID_BODY = "1";
public static final String WITH_EMPTY_ARRAY = "2";
public static final String WITH_NULL = "3";
public static final String DELETE_WITH_BODY = "4";

	public Utility() {
		RestAssured.baseURI = "http://localhost:7894";
		RestAssured.basePath = "v3";
	}

	public Response generateIds(int howMany) {
		String generateIdsGet = "generate/" + howMany;
		return RestAssured.given().get(generateIdsGet).then().log().all().extract().response();
	}

	public Response getIds() {
		String fetchGet = "ids";
		return RestAssured.given().get(fetchGet).then().log().all().extract().response();
	}

	public Response initIds(String method ,String... ids) {
		String saveIdsPost = "ids";
		String body = stringsToBody(ids);
		if (method == null)
			method="";
		switch (method) {
		case WITHOUT_BODY:
			return RestAssured.given().when().post(saveIdsPost)
					.then().log().all().extract().response();
		case WITH_INVALID_BODY:
			String invalidBody = "{\"invalid\":[true]}";
			return RestAssured.given().contentType(ContentType.JSON).body(invalidBody).when().post(saveIdsPost)
					.then().log().all().extract().response();
		case WITH_EMPTY_ARRAY:
			String emptyArray = "[]";
			return RestAssured.given().contentType(ContentType.JSON).body(emptyArray).when().post(saveIdsPost)
					.then().log().all().extract().response();
		case WITH_NULL:
			String _null = null;
			return RestAssured.given().contentType(ContentType.JSON).body(_null).when().post(saveIdsPost)
					.then().log().all().extract().response();

		default:
			return RestAssured.given().contentType(ContentType.JSON).body(body).when().post(saveIdsPost)
					.then().log().all().extract().response();
		}
		
	}
	

	public Response updateIds(String method, String... ids) {
		String updatePut = "ids";
		String body = stringsToBody(ids);
		if(method==null)
			method="";
		switch (method) {
		case WITHOUT_BODY:
			return RestAssured.given().when().post(updatePut)
					.then().log().all().extract().response();
		case WITH_INVALID_BODY:
			String invalidBody = "{\"invalid\":[true]}";
			return RestAssured.given().contentType(ContentType.JSON).body(invalidBody).when().post(updatePut)
					.then().log().all().extract().response();
		case WITH_EMPTY_ARRAY:
			String emptyArray = "[]";
			return RestAssured.given().contentType(ContentType.JSON).body(emptyArray).when().post(updatePut)
					.then().log().all().extract().response();
		case WITH_NULL:
			String _null = null;
			return RestAssured.given().contentType(ContentType.JSON).body(_null).when().post(updatePut)
					.then().log().all().extract().response();

		default:
			return RestAssured.given().contentType(ContentType.JSON).body(body).when().put(updatePut)
					.then().log().all().extract().response();
		}
		
	}

	public Response deleteId(String method, String id) {
		
		String deleteDelete = "ids/" + id;
		if(method==null)
			method="";
		switch (method) {
		case DELETE_WITH_BODY:
			return RestAssured.given().contentType(ContentType.JSON).body("[\"BODY\"]").when().delete(deleteDelete).then().log().all().log().all().extract().response();

		default:
			return RestAssured.given().delete(deleteDelete).then().log().all().log().all().extract().response();
		}
	}

	public void print(Response response) {
		System.out.println(response.getStatusCode());
		response.prettyPrint();
		System.out.println("\n-----------\n");
	}

	private String stringsToBody(String... ids) {
		return new Gson().toJson(ids);

	}
}

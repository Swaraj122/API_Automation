package RequestCollection;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Post {
	@Ignore
	@Test
	public void TC1() {

		RestAssured.baseURI = "http://localhost:3000/";
		Response resp = given()

				.contentType(ContentType.JSON)

				.body("{\n" + "      \"id\": 7,\n" + "      \"title\": \"json-server\",\n"
						+ "      \"author\": \"Apple2\"\n" + "    }")
				.when().post("/posts");

		assertEquals(resp.getStatusCode(), 201);

		Response Resp = given().get("/posts/7").then().extract().response();
		// Response is in json format
		// chnage response to String
		assertEquals(Resp.getStatusCode(), 200);
		assertEquals(Resp.jsonPath().getString("id"), "7");
		assertEquals(Resp.jsonPath().getString("title"), "json-server");
	}

}

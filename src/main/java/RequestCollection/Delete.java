package RequestCollection;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Delete {
	
	@Test
	public void TC1() {
		
		//Creation
		
		RestAssured.baseURI="http://localhost:3000";
		
		Response Resp1=given().contentType(ContentType.JSON)
				.body(" {\n"
						+ "      \"id\": 5,\n"
						+ "      \"title\": \"json-server\",\n"
						+ "      \"author\": \"Apple2\"\n"
						+ "    }")
				.post("/posts");
		
		
		assertEquals(Resp1.getStatusCode(),201);
		given().get("/posts").then().log().all();
		String NewlyAddedPostId=Resp1.jsonPath().getString("id");
		//Deletion
		Response RespoOfDeletionReq = given().delete("/posts/" + NewlyAddedPostId +"");
		
		assertEquals(RespoOfDeletionReq.statusCode(),200);
		System.out.println("The Status code after deletion is ");
		
		
		
	}

}

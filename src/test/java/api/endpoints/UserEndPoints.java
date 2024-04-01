package api.endpoints;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//This is UserEndPoint.Java End point Java class
//Created for perform the Create, Read, Update and Delete Requests 

public class UserEndPoints {

	public static Response createUser(User payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_user_url);
		
		return response;
	}
	
	public static Response readUser(String userName)
	{
		Response response = given()
				.pathParam("username", userName)
		.when()
			.get(Routes.get_user_url);
		
		return response;
	}
	
	
	public static Response updateUser(String userName, User payload)
	{
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(Routes.put_user_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
				.pathParam("username", userName)
		.when()
			.delete(Routes.delete_user_url);
		
		return response;
	}
}

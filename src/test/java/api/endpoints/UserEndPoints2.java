package api.endpoints;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Properties;
import java.util.ResourceBundle;

//This is UserEndPoint.Java End point Java class
//Created for perform the Create, Read, Update and Delete Requests 

public class UserEndPoints2 {
	
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");
//		Properties prop = new Properties();
		return routes;
	}

	public static Response createUser(User payload)
	{
		String post_user_url = getURL().getString("post_user_url");
		
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
//			.post(Routes.post_user_url);
			.post(post_user_url);
		
		return response;
	}
	
	public static Response readUser(String userName)
	{
		String get_user_url = getURL().getString("get_user_url");
		
		Response response = given()
				.pathParam("username", userName)
		.when()
//			.get(Routes.get_user_url);
			.get(get_user_url);
		
		return response;
	}
	
	
	public static Response updateUser(String userName, User payload)
	{
		String put_user_url = getURL().getString("put_user_url");
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
//			.put(Routes.put_user_url);
			.put("put_user_url");
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_user_url = getURL().getString("delete_user_url");
		Response response = given()
				.pathParam("username", userName)
		.when()
//			.delete(Routes.delete_user_url);
			.delete("delete_user_url");
		
		return response;
	}
}

package api.endpoints;

/*
  Swagger URI --> https://petstore.swagger.io
  Create User(Post) --> https://petstore.swagger.io/v2/user/createUser
  Get User(Get) --> https://petstore.swagger.io/v2/user/{username}
  Update User (Put) --> https://petstore.swagger.io/v2/user/{username}
  Delete User(Delete) --> https://petstore.swagger.io/v2/user/{username}
 */


public class Routes {

	public static String base_url = "https://petstore.swagger.io/v2";
	
	//user module Urls with endpoitns
	public static String post_user_url = base_url+"/user";
	public static String get_user_url = base_url+"/user/{username}";
	public static String put_user_url = base_url+"/user/{username}";
	public static String delete_user_url = base_url+"/user/{username}";
}

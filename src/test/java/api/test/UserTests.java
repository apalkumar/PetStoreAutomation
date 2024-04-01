package api.test;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	public Logger logger;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPhone(faker.phoneNumber().phoneNumber());
		userPayload.setPassword(faker.internet().password(5, 10));
		
		logger= org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void testPostUser() {
		
		logger.info("--------------- Creating User-----------");

		Response respone = UserEndPoints.createUser(userPayload);
		System.out.println("Hi This is First Response");
		respone.then().log().all();
		System.out.println(userPayload.getUsername());
		Assert.assertEquals(respone.getStatusCode(), 200);
	
		logger.info("--------------- User Created-----------");
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("--------------- Getting Details for User-----------");
		
		Response respone = UserEndPoints.readUser(this.userPayload.getUsername());
		System.out.println("Hi This is Second Response");
		respone.then().log().all();
		Assert.assertEquals(respone.getStatusCode(), 200);
		logger.info("--------------- User Details Got-----------");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.info("--------------- Updating User-----------");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response respone = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		respone.then().log().body().statusCode(200);
		System.out.println("Hi This is third Response");
		respone.then().log().all();
//		Assert.assertEquals(respone.getStatusCode(), 200);
		Response responeAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responeAfterUpdate.getStatusCode(), 200);
		
		String strPrint = responeAfterUpdate.prettyPrint();
		String[] strPrintArray = new String[] {strPrint};
		System.out.println("Response Body -->"+Arrays.toString(strPrintArray));
//		System.out.println("Status Line"+respone.statusLine());
		
		logger.info("--------------- User Updated-----------");
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		
		logger.info("--------------- Deleting  User-----------");
		Response respone = UserEndPoints.deleteUser(this.userPayload.getUsername());
		System.out.println("Hi This is fourth Response");
		respone.then().log().all();
		Assert.assertEquals(respone.getStatusCode(), 200);
		
		String strPrint = respone.prettyPrint();
		String[] strPrintArray = new String[] {strPrint};
		System.out.println("Response Body -->"+Arrays.toString(strPrintArray));
		logger.info("--------------- User Deleted-----------");

	}

}

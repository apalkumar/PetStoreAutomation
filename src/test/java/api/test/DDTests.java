package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	Faker faker;

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID, String UserName, String FirstName, String LastName, String Email,
			String Password, String ph) {
		faker = new Faker();
		User userPayload = new User();
//		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(UserName);
		userPayload.setFirstname(FirstName);
		userPayload.setLastname(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(ph);
		
		Response respone = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(respone.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response respone = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(respone.getStatusCode(), 200);
	}

	
}

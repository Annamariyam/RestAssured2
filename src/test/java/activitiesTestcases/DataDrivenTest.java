package activitiesTestcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import activitesUtilities.DataProviders;
import activitiesEndPoints.Routes;
import activitiesEndPoints.UserEndPoints;
import activitiesPayload.UserModel;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

/**
 * This class contains test methods for testing user-related API endpoints.
 */
@Listeners(activitesUtilities.ExtentReportManager.class)
public class DataDrivenTest {
	UserModel userPayload;

	// Test method to create a user using data from the "data" data provider
	@Test(priority = 1, dataProvider = "data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String title, String dueDate) {
		RestAssured.useRelaxedHTTPSValidation();
		UserModel user = new UserModel();
		user.setId(Integer.parseInt(userID));
		user.setTitle(title);
		user.setDueDate(dueDate);
		Response response = UserEndPoints.createUser(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	// Test method to get a user by ID using data from the "ids" data provider
	@Test(priority = 2, dataProvider = "ids", dataProviderClass = DataProviders.class)
	public void testGetUserById(String id) {
		Response response = UserEndPoints.getUser(Integer.parseInt(id));
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	// Test method to get users using data from the "data" data provider
	@Test(priority = 3, dataProvider = "data", dataProviderClass = DataProviders.class)
	public void testGetUser(String userID, String title, String dueDate) {
		Response response = UserEndPoints.getUser1();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	// Test method to update a user by ID using data from the "updates" data
	// provider
	@Test(priority = 4, dataProvider = "updates", dataProviderClass = DataProviders.class)
	public void testUpdateUserByID(String userID, String title, String dueDate) {
		RestAssured.useRelaxedHTTPSValidation();
		UserModel user = new UserModel();
		user.setId(Integer.parseInt(userID));
		user.setTitle(title);
		user.setDueDate(dueDate);
		Response response = UserEndPoints.updateUser(Integer.parseInt(userID), user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	// Test method to delete users using data from the "ids" data provider
	@Test(priority = 5, dataProvider = "ids", dataProviderClass = DataProviders.class)
	public void testDeleteByUserID(String id) {
		// Delete a user by ID using the UserEndPoints class
		Response response = UserEndPoints.deleteUser(Integer.parseInt(id)); // Convert string to int

		// Log response details and assert the response status code
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 6)
    public void schemavalidation() {
    	
    	RestAssured.given()
    	.pathParam("id", 12)
        .when()
        .get(Routes.get_basePath)
        .then()
        .assertThat()
        .body(JsonSchemaValidator.matchesJsonSchema(System.getProperty("user.dir") + "\\src\\main\\resources\\activities.json"));
    }

}

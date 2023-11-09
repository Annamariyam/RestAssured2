package activitiesEndPoints;

import activitiesPayload.UserModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// This class is created to perform CRUD(create read update delete) operations in user API
public class UserEndPoints {

	// Method to create a new user
	public static Response createUser(UserModel payload) {
		// Enable relaxed HTTPS validation to handle different SSL certificate scenarios
		RestAssured.useRelaxedHTTPSValidation();

		Response response = RestAssured.given()
				// Set the base URI for the API
				.baseUri(Routes.baseuri) 
				// Set the endpoint path for retrieving activity details by ID
				.basePath(Routes.post_basePath)
				// Set the request content type as JSON
				.contentType("application/json")
				.accept(ContentType.JSON).body(payload).when().post();

		return response;// Return the API response
	}

	// Method to retrieve a user by their ID
	public static Response getUser(int id) {
		RestAssured.useRelaxedHTTPSValidation();
		Response response = RestAssured.given().baseUri(Routes.baseuri)
				.basePath(Routes.get_basePath)
				.pathParam("id", id)// Set the path parameter for the activities ID
				.contentType("application/json").accept(ContentType.JSON).when()
				.get();// Perform the GET request

		return response;
	}

	// Method to retrieve users using a different endpoint
	public static Response getUser1() {
		RestAssured.useRelaxedHTTPSValidation();
		Response response = RestAssured.given().baseUri(Routes.baseuri).basePath(Routes.get_basePath1)
				.contentType("application/json").accept(ContentType.JSON).when().get();

		return response;
	}

	// Method to delete a user by their ID
	public static Response deleteUser(int id) {
		RestAssured.useRelaxedHTTPSValidation();
		Response response = RestAssured.given().baseUri(Routes.baseuri).basePath(Routes.delete_basePath)
				.pathParam("id", id).contentType("application/json").accept(ContentType.JSON).when().delete();

		return response;
	}

	/**
	 * Updates a user's information by sending a PUT request to the specified API
	 * endpoint.
	 *
	 * @param id      The ID of the user to be updated.
	 * @param payload The UserModel object containing the updated user information.
	 * @return A Response object containing the API response after the update
	 *         request.
	 */
	public static Response updateUser(int id, UserModel payload) {
		// Enable relaxed HTTPS validation to handle different SSL certificate scenarios
		RestAssured.useRelaxedHTTPSValidation();
		// Send a PUT request to the API endpoint to update the user information
		Response response = RestAssured.given().baseUri(Routes.baseuri).basePath(Routes.put_basePath)
				.pathParam("id", id).contentType("application/json").accept(ContentType.JSON).body(payload).when()
				.put();

		return response;
	}

}

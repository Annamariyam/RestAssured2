package activitiesEndPoints;
/**
 * This class defines constants for the endpoints used in the application.
 */
public class Routes {
    // The base URI for the API
    public static String baseuri = "https://fakerestapi.azurewebsites.net/api/v1";

    // Endpoint for creating a new activity (POST request)
    public static String post_basePath = "/Activities";

    // Endpoint for retrieving a specific activity by ID (GET request)
    public static String get_basePath = "/Activities/{id}";

    // Endpoint for retrieving a list of activities (GET request)
    public static String get_basePath1 = "/Activities";

    // Endpoint for deleting a specific activity by ID (DELETE request)
    public static String delete_basePath = "/Activities/{id}";

    // Endpoint for updating a specific activity by ID (PUT request)
    public static String put_basePath = "/Activities/{id}";
}

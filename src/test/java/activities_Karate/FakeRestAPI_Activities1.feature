Feature: API Testing
    # Describes the feature being tested: API Testing

Background:
    * configure ssl = true
    * url 'https://fakerestapi.azurewebsites.net/api/v1/'
    # Background steps that apply to all scenarios: configure SSL and set the base URL

Scenario: Verify Environment Setup
    # A scenario to verify that the environment is set up correctly
    Given url 'https://fakerestapi.azurewebsites.net/api/v1/Activities'
    When method GET
    Then status 200
    # Validate that the environment is correctly configured and the base endpoint responds with status 200

Scenario: Perform GET Request and Validate Response
    # A scenario to perform a GET request and validate the response
    Given path 'Activities' , '1'
    When method GET
    Then status 200
    And match response.id == 1
    And match response.title == 'Activity 1'
    # Validate that the response for the GET request to a specific activity matches the expected values

Scenario: Use Built-In Assertions for Response Validation
    # A scenario to use built-in assertions for response validation
    Given path 'Activities', '16'
    When method GET
    Then status 200
    And match response.id == 16
    And match response.title contains 'Activity 16'
    # Validate response using built-in assertions: check if the ID is 16 and the title contains 'Activity 16'
    
Scenario: Send POST Request with JSON Payload
    # A scenario to send a POST request with JSON payload
    Given path 'Activities'
    And request {"id":31, "title":"Module Exam", "dueDate":"2023-08-19T01:19:51.5537558+00:00", "completed":false}
    When method POST
    Then status 200
    # Send a POST request to create a new user with specific data and validate the response status

Scenario: Handle and Validate Error Responses
    # A scenario to handle and validate error responses
    Given path 'non-existing-endpoint'
    When method GET
    Then status 404
    # Send a GET request to a non-existing endpoint and validate the expected 404 error response

Scenario: Invalid User
    # A scenario to perform GET request for an invalid user
    Given path 'Activities', '1678902'
    When method GET
    Then status 404
    # Send a GET request for an invalid user and validate the expected 404 error response

Scenario: Perform PUT Request to Update a Post
    # A scenario to perform a PUT request to update an existing post
    Given path 'Activities', '1'
    And request {"id":1, "title":"Updated Title", "dueDate":"2023-08-19T06:19:51.553759+00:00","completed":true}
    When method PUT
    Then status 200
    # Send a PUT request to update a post and validate the response status

Scenario: Perform DELETE Request to Delete a Post
    # A scenario to perform a DELETE request to delete an existing post
    Given path 'Activities', '10'
    When method DELETE
    Then status 200
    # Send a DELETE request to delete a post and validate the response status

Scenario: Send PUT Request with JSON Payload
    # A scenario to send a PUT request with JSON payload
    Given path 'Activities'
    And request {"id":1, "title":"New Title", "dueDate":"2024-08-19T06:29:55.553759+00:00","completed":false}
    When method POST
    Then status 200
    # Send a PUT request to update an activity's details and validate the response status


Scenario Outline: Data-Driven Testing with Data Table
    #A scenario outline for data-driven testing with examples
    Given path 'Activities'
    And request { "id": "<id>", "title": "<title>" ,"dueDate":"<dueDate>","completed":<completed>}
    When method POST
    Then status 200
    Examples:
        | id | title         | dueDate                            | completed |
        | 1  | Activity 1    | 2023-08-18T05:19:51.5537476+00:00 | true      |
        | 2  | Activity 2    | 2023-08-19T06:19:51.553759+00:00 | false     |
     #Perform data-driven testing using examples from the table, filling in placeholders in the request    


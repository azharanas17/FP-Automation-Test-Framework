@api
Feature: User API Operations on DummyAPI
  As an API consumer
  I want to perform CRUD operations on users
  So that I can manage user data

  Scenario: Get list of users
    Given the API base URL is set
    When I send a GET request to "/user?limit=1"
    Then the response status code should be 200
    And the response should contain a user with valid data

  Scenario: Get a single user by ID
    Given the API base URL is set
    When I send a GET request to "/user?limit=1"
    And I extract the user ID from the response
    When I send a GET request to the extracted user by ID
    Then the response status code should be 200
    And the response should contain detailed user data

  Scenario: Create a new user
    Given the API base URL is set
    When I send a POST request to "/user/create" with body:
      """
      {
        "firstName": "John",
        "lastName": "Doe",
        "email": "johndoe_create_test@example.com",
        "phone": "+1234567890"
      }
      """
    Then the response status code should be 200
    And the response should contain the created user with firstName "John"

  Scenario: Update an existing user
    Given the API base URL is set
    When I send a GET request to "/user?limit=1"
    And I extract the user ID from the response
    When I send a PUT request to the extracted user ID with body:
      """
      {
        "firstName": "Updated",
        "lastName": "User"
      }
      """
    Then the response status code should be 200
    And the response should contain the updated user with firstName "Updated"

  Scenario: Delete a user by creating and deleting
    Given the API base URL is set
    When I send a POST request to "/user/create" with body:
      """
      {
        "firstName": "ToDelete",
        "lastName": "User",
        "email": "todelete_user_test@example.com",
        "phone": "+9876543210"
      }
      """
    Then the response status code should be 200
    When I delete the newly created user
    Then the response status code should be 200

  Scenario: Get user with invalid ID
    Given the API base URL is set
    When I send a GET request to "/user/invalidid123"
    Then the response status code should be 404
    And the error message should be "RESOURCE_NOT_FOUND"

@api
Feature: Tag API Operations on DummyAPI
  As an API consumer
  I want to retrieve list of tags
  So that I can use them for filtering content

  Scenario: Get list of tags
    Given the API base URL is set
    When I send a GET request to "/tag"
    Then the response status code should be 200
    And the response should contain a list of tags in data field

  Scenario: Get tags with page parameter
    Given the API base URL is set
    When I send a GET request to "/tag?page=0"
    Then the response status code should be 200
    And the response should contain a list of tags in data field

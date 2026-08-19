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

  Scenario Outline: Get tags with different page values
    Given the API base URL is set
    When I send a GET request to "/tag?page=<page>"
    Then the response status code should be 200
    And the response should contain a list of tags in data field

    Examples:
      | page |
      | 0    |
      | 1    |

  Scenario: Verify each tag has id and value
    Given the API base URL is set
    When I send a GET request to "/tag"
    Then the response status code should be 200
    And each tag should have an id and a value

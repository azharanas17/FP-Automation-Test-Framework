@web
Feature: Login Functionality on Demoblaze
  As a user
  I want to log in to my account
  So that I can access my profile and make purchases

  Background:
    Given the user is on the Demoblaze homepage

  Scenario: Successful login with valid credentials
    When the user signs up with username "testuser123asdsds" and password "Test@1234"
    Then the signup alert should appear with message "Sign up successful."
    When the user clicks the login link
    And the user enters username "testuser123asdsds" and password "Test@1234"
    And the user clicks the login button
    Then the user should be logged in successfully

  Scenario: Failed login with invalid credentials
    When the user clicks the login link
    And the user enters username "nonexistent9x7k2m" and password "wrongpassword"
    And the user clicks the login button
    Then an alert should appear with message "User does not exist."

  Scenario: Failed login with empty credentials
    When the user clicks the login link
    And the user enters username "" and password ""
    And the user clicks the login button
    Then an alert should appear with message "Please fill out Username and Password."

  Scenario: Failed login with only username provided
    When the user clicks the login link
    And the user enters username "someuser" and password ""
    And the user clicks the login button
    Then an alert should appear with message "Please fill out Username and Password."

  Scenario Outline: Failed login with various invalid credentials
    When the user clicks the login link
    And the user enters username "<username>" and password "<password>"
    And the user clicks the login button
    Then an alert should appear with message "<expectedMessage>"

    Examples:
      | username          | password        | expectedMessage              |
      | nonexistentuser1  | wrongpass       | User does not exist.         |
      | <script>alert(1)  | Test@1234       | User does not exist.         |
      | admin'; --        | ' OR 1=1 --     | User does not exist.         |

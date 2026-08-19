@web
Feature: Shopping Cart on Demoblaze
  As a user
  I want to add products to my cart
  So that I can purchase them later

  Background:
    Given the user is on the Demoblaze homepage

  Scenario: Add a product to cart
    When the user clicks on the first product
    And the user adds the product to cart
    Then the product should be added to the cart successfully

  Scenario: View cart contents
    When the user clicks on the first product
    And the user adds the product to cart
    And the user navigates to the cart page
    Then the cart should contain at least 1 item

  Scenario: Complete checkout process
    When the user clicks on the first product
    And the user adds the product to cart
    And the user navigates to the cart page
    And the user clicks place order
    And the user fills checkout form with name "John Doe", country "Indonesia", city "Jakarta", card "4111111111111111", month "12", year "2028"
    And the user clicks purchase
    Then the confirmation message should be displayed

  Scenario: Add multiple products to cart
    When the user clicks on the first product
    And the user adds the product to cart
    When the user clicks the home link
    And the user clicks on the second product
    And the user adds the product to cart
    And the user navigates to the cart page
    Then the cart should contain 2 items

  Scenario: Verify cart total price is displayed
    When the user clicks on the first product
    And the user adds the product to cart
    And the user navigates to the cart page
    Then the cart total price should be displayed

  Scenario Outline: Checkout with empty required fields
    When the user clicks on the first product
    And the user adds the product to cart
    And the user navigates to the cart page
    And the user clicks place order
    And the user fills checkout form with name "<name>", country "<country>", city "<city>", card "<card>", month "<month>", year "<year>"
    And the user clicks purchase
    Then an alert should appear with message "<expectedMessage>"

    Examples:
      | name | country | city     | card              | month | year | expectedMessage                |
      |      | USA     | New York | 4111111111111111  | 12    | 2028 | Please fill out Name and Card.  |
      | John |         |          |                   |       |      | Please fill out Name and Card.  |
      | John | USA     | New York |                   |       |      | Please fill out Name and Card.  |

  Scenario: Delete item from cart
    When the user clicks on the first product
    And the user adds the product to cart
    And the user navigates to the cart page
    And the user deletes the first item from the cart
    Then the cart should be empty

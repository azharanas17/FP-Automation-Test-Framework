@web
Feature: Product Browsing on Demoblaze
  As a user
  I want to browse products
  So that I can find items to purchase

  Background:
    Given the user is on the Demoblaze homepage

  Scenario: Verify products are displayed on homepage
    Then the page title should be "STORE"
    And the product list should not be empty

  Scenario: View product details
    When the user clicks on the first product
    Then the product detail page should be displayed
    And the product should have a name, price and description

  Scenario: Navigate to a specific product by name
    When the user clicks on product "Samsung galaxy s6"
    Then the product detail page should be displayed
    And the product name should be "Samsung galaxy s6"

  Scenario: Verify product count on homepage
    Then the product list should not be empty
    And the product count should be greater than 0

  Scenario: Navigate back to homepage from product detail
    When the user clicks on the first product
    Then the product detail page should be displayed
    When the user clicks the home link
    Then the user should be on the homepage

  Scenario Outline: Filter products by category
    When the user clicks on the "<category>" category
    Then the product list should not be empty
    And the product list should show products from "<category>" category

    Examples:
      | category |
      | Phones   |
      | Laptops  |
      | Monitors |

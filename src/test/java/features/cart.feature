Feature: Shopping Cart functionality

  Scenario: Add item to cart
    Given user is logged in
    When user adds an item to the cart
    Then the cart badge should show "1"

  Scenario: Remove item from cart
    Given user has one item in the cart
    When user removes the item
    Then the cart badge should not be visible

Feature: Login to Swag Labs

  Scenario: Valid login
    Given user is on the login page
    When user enters valid username and password
    And clicks the login button
    Then user should be redirected to the inventory page

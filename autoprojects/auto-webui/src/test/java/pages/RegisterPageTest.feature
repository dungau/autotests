Feature: Register User

  Scenario: Register user with blank data
    Given User navigate to register page
    And Click register button
    Then User see list error messages

  Scenario: Register user with inconsistent passwords
    Given User navigate to register page
    When User enter data with inconsistent passwords
    And Click register button
    Then User see the inconsistent passwords message

  Scenario: Register user with valid data
    Given User navigate to register page
    When User enter valid data
    And Click register button
    Then User can see welcome message with registered username
Feature: Login

  Scenario: Login with blank credential
    Given User in Login page
    When User enter blank credential
    And Click Login button
    Then User get the validate error message

  Scenario: Login with invalid credential
    Given User in Login page
    When User enter invalid credential
    And Click Login button
    Then User get the wrong credential message

  Scenario: Login with valid credential
    Given User in Login page
    When User enter valid credential
    And Click Login button
    Then User in Welcome page

  Scenario: Logout after logged in successfully
    Given User in Login page
    When User enter valid credential
    And Click Login button
    When User in Welcome page
    And Click logout button
    Then User in Login page

Feature: Create new Account

  Background:
    Given User enter valid credential test09
    And Click Login button

  Scenario: Verify the logged user info
    Given User in Welcome page
    When Click on Open new Account link
    And Click on Open new Account button
    And Click on Accounts Overview link
    Then Verify the account information is corrected

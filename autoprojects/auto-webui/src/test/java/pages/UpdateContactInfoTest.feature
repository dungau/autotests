Feature: Update Contact Info

  Background:
    Given User enter valid credential
    And Click Login button

  Scenario: Verify the logged user info
    Given User in Welcome page
    When Click update contact info link
    Then User see all information are matched

  Scenario: Update user contact info with blank data
    Given User in Welcome page
    When Click update contact info link
    And Update contact info with blank data
    Then User see list validation error messages

  Scenario: Update user info with valid data
    Given User in Welcome page
    When Click update contact info link
    And Update contact info with valid data
    Then User see the update successfully message

  Scenario: Revert user info with origin data
    Given User in Welcome page
    When Click update contact info link
    And Revert contact info with origin data
    Then User see the update successfully message

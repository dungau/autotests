Feature: Verify GetAccount Feature
  
  Scenario: Verify Getting Account Info Successfully
    Given I want to execute get account info endpoint with valid params
    When I submit the GET request
    Then I should get 200OK
    And Get all information like balance and account id

Feature: Verify Login Feature 
 
  Scenario: Verify login with un-register account unsuccessfully
    Given I want to execute Login endpoint with the un-registered username "test00" and password "test00"
    When I submit the POST request
    Then I should get 200 OK
    And Get the error page


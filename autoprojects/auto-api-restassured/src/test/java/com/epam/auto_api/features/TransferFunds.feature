Feature: Verify transfer funds from this account to another account
    
  Scenario: Verify transfer funds successfully
    Given I want to have the current balance of this account
    And Execute transfer funds endpoint with amount 50
    When I submit the transfer POST request
    And Get the new balance of this account
    Then I have the successful response message
    And Should get the response code 200 OK
		And The new balance is less than previous balance 50
 

    


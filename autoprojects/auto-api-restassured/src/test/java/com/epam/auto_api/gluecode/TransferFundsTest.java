package com.epam.auto_api.gluecode;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.auto_api.util.Configs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TransferFundsTest extends BaseTest {
	private static final String TRANSFER_PAGE = "/parabank/services_proxy/bank/transfer";
	private Response response;
	private Double previousBlance;
	private Double amount;

	@Given("^I want to have the current balance of this account$")
	public void getTheCurrentBalance() throws Throwable {
		this.previousBlance = account.getBalance();

	}

	@Given("^Execute transfer funds endpoint with amount (\\d+)$")
	public void prepareTheTransferFundsEndpointAndAmount(double amount) throws Throwable {
		RestAssured.baseURI = Configs.getAsString("baseURI");
		RestAssured.basePath = TRANSFER_PAGE;
		this.amount = amount;
	}

	@When("^I submit the transfer POST request$")
	public void submitTheTransferPostRequest() throws Throwable {
		Response response = RestAssured.given().header("cookie", sessionId).param("fromAccountId", account.getId())
				.param("toAccountId", Configs.getAsString("receiver.accountId")).param("amount", this.amount).post();
		this.response = response;

	}

	@When("^Get the new balance of this account$")
	public void getTheNewBalance() throws Throwable {
		getResponseAndUpdateAccountInfo();
	}

	@Then("^I have the successful response message$")
	public void getSuccessfulResponseMessage() throws Throwable {
		Assert.assertEquals(response.getBody().asString().contains("Successfully transferred"), true);
	}

	@And("^Should get the response code (\\d+) OK$")
	public void getTheResponseCode(int expectedStatus) throws Throwable {
		Assert.assertEquals(response.statusCode(), expectedStatus);
	}

	@And("^The new balance is less than previous balance (\\d+)$")
	public void verifyCurrentBalanceAndPreviousBalance(double funds) throws Throwable {
		double currentBalance = account.getBalance();
		Assert.assertEquals(previousBlance - funds, currentBalance);
	}
}

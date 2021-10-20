package com.epam.auto_api.gluecode;

import org.testng.Assert;

import com.epam.auto_api.om.Account;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class GetAccountInfoTest extends BaseTest {
	private Response response;
	// protected static Account account;

	@Given("^I want to execute get account info endpoint with valid params$")
	public void prepareGetAccountInfoEndpoint() throws Throwable {

	}

	@When("^I submit the GET request$")
	public void submitGetRequest() throws Throwable {
		Response response = getResponseAndUpdateAccountInfo();
		this.response = response;

	}

	@Then("^I should get 200OK$")
	public void get200OK() throws Throwable {
		Assert.assertEquals(this.response.statusCode(), 200);

	}

	@Then("^Get all information like balance and account id$")
	public void messageBodyIsNotNull() throws Throwable {
		Assert.assertNotNull(account.getBalance());
		Assert.assertNotNull(account.getId());
	}

}

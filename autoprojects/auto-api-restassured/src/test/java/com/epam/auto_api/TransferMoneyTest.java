package com.epam.auto_api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import util.Configs;

class TransferMoneyTestCases extends BaseTestCases {
	private static final String TRANSFER_PAGE = "/parabank/services_proxy/bank/transfer";

	@Test
	public void verifyTransferSuccessfully() {
		Double firstBalance = account.getBalance();
		Response response = RestAssured.given().header("cookie", sessionId).param("fromAccountId", account.getId())
				.param("toAccountId", Configs.getAsString("receiver.accountId")).param("amount", 50)
				.post(TRANSFER_PAGE);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(response.getBody().asString().contains("Successfully transferred"), true);

		getResponseAndUpdateAccountInfo();

		double currentBalance = account.getBalance();
		Assert.assertEquals(firstBalance - 50, currentBalance);
	}

}

package com.epam.auto_api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TransferMoneyTestCases extends BaseTestCases {
	private static final String TRANSFER_PAGE = "/parabank/services_proxy/bank/transfer";
	protected static final String TRANSFER_TO_ACCOUNT = "13899";

	@Test
	public void verifyTransferSuccessfully() {
		Double firstBalance = account.getBalance();
		Response response = RestAssured.given().header("cookie", sessionId).param("fromAccountId", account.getId())
				.param("toAccountId", TRANSFER_TO_ACCOUNT).param("amount", 50).post(TRANSFER_PAGE);
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(response.getBody().asString().contains("Successfully transferred"), true);
		getResponseAndUpdateAccountInfo();
		double currentBalance = account.getBalance();
		Assert.assertEquals(firstBalance - 50, currentBalance);
	}

}

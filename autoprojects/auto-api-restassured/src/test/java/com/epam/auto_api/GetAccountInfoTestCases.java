package com.epam.auto_api;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class GetAccountInfoTestCases extends BaseTestCases {
	@Test()
	public void verifyGetAccountIdSuccessfull() {
		Response response = getResponseAndUpdateAccountInfo();
		Assert.assertEquals(response.statusCode(), 200);
	}
}

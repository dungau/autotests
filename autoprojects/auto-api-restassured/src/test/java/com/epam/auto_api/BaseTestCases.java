package com.epam.auto_api;

import java.util.List;

import org.testng.annotations.BeforeTest;

import com.epam.auto_api.om.Account;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import util.Configs;

public class BaseTestCases {
	private static final String GET_ACCOUNT_INFO_PAGE = "/parabank/services_proxy/bank/customers/%s/accounts";
	protected static String sessionId;
	protected static String customerId;
	protected static Account account;
	

	@BeforeTest
	static void setUpBeforeClass() throws Exception {
		RestAssured.baseURI = Configs.getAsString("baseURI");
	}

	
	public Response getResponseAndUpdateAccountInfo() {
		Response response = RestAssured.given().header("cookie", sessionId)
				.get(String.format(GET_ACCOUNT_INFO_PAGE, customerId));
		JsonPath jsonPathEvaluator = response.jsonPath();
		List<Account> accountList = jsonPathEvaluator.getList("", Account.class);
		account = accountList.get(0);
		return response;
	}
}

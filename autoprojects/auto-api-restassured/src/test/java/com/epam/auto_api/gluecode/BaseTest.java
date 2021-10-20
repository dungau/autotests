package com.epam.auto_api.gluecode;

import java.util.List;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.epam.auto_api.om.Account;
import com.epam.auto_api.util.Configs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseTest {
	private static final String GET_ACCOUNT_INFO_PAGE = "/parabank/services_proxy/bank/customers/%s/accounts";
	public static String sessionId;
	public static String customerId;
	public static Account account;

		public Response getResponseAndUpdateAccountInfo() {
		// Call API to get all account info of a customer
		RestAssured.baseURI = Configs.getAsString("baseURI");
		RestAssured.basePath = String.format(GET_ACCOUNT_INFO_PAGE, customerId);
		Response response = RestAssured.given().log().all().header("cookie", sessionId)
				.get();
		// Get all json data from response and put to Account class
		JsonPath jsonPathEvaluator = response.jsonPath();
		List<Account> accountList = jsonPathEvaluator.getList("", Account.class);
		account = accountList.get(0);
		return response;
	}
}

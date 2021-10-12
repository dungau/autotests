package com.epam.auto_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthenticationTestCases extends BaseTestCases {
	private static final String LOGIN_PAGE = "/parabank/login.htm";

	@Test()
	public void verifyValidAccLogin() {

		Response response = RestAssured.given().log().all().contentType(ContentType.URLENC.withCharset("UTF-8"))
				.formParam("username", "test01").formParam("password", "test01").post(LOGIN_PAGE);

		String header = response.getHeader("Location");
		String[] pairLocationAndSessionId = StringUtils.split(header, ";");
		String location = pairLocationAndSessionId[0];
		String sessionId = pairLocationAndSessionId[1];
		BaseTestCases.sessionId = sessionId.toUpperCase();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 302);

		Response redirectResponse = RestAssured.given().header("cookie", BaseTestCases.sessionId).get(location);
		String customerId = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(redirectResponse.getBody().asInputStream()));

			String line;
			while ((line = br.readLine()) != null) {
				if (line != null && line.contains("services_proxy/bank/customers/")) {
					customerId = line.substring(line.indexOf("+") + 1, line.lastIndexOf("+")).trim();
				}
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		BaseTestCases.customerId = customerId;
	}
}

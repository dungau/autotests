package com.epam.auto_api.runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

import com.epam.auto_api.gluecode.BaseTest;
import com.epam.auto_api.util.Configs;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@CucumberOptions(
		features = "src/test/java/com/epam/auto_api/features",
		glue = {"com.epam.auto_api.gluecode"},
		plugin = {"pretty","com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
		monochrome = true
		)


public class TestRunner extends AbstractTestNGCucumberTests {
	@BeforeSuite
	public void verifyValidAccLogin() {
		RestAssured.baseURI = Configs.getAsString("baseURI");
		Response response = RestAssured.given().log().all().contentType(ContentType.URLENC.withCharset("UTF-8"))
				.formParam("username", Configs.getAsString("login.username"))
				.formParam("password", Configs.getAsString("login.password")).post("/parabank/login.htm");

		String header = response.getHeader("Location");
		String[] pairLocationAndSessionId = StringUtils.split(header, ";");
		String location = pairLocationAndSessionId[0];
		String sessionId = pairLocationAndSessionId[1];
		BaseTest.sessionId = sessionId.toUpperCase();
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 302);

		Response redirectResponse = RestAssured.given().header("cookie", BaseTest.sessionId).get(location);
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
		BaseTest.customerId = customerId;
	}
	@AfterClass
     public static void teardown() {
         
     }
}

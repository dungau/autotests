package com.epam.auto_api.gluecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import com.epam.auto_api.util.Configs;
import com.github.fge.jsonschema.main.cli.Main;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class AuthenticationTest extends BaseTest {
	private static final String LOGIN_PAGE = "/parabank/login.htm";

	private Response response;
	private String username;
	private String password;
	
	public static void main(String[] args) {
		System.out.println("abc");
	}

	@Given("^I want to execute Login endpoint with the un-registered username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void prepareLoginEndPointAndAccount(String username, String password) throws Throwable {
		RestAssured.basePath = LOGIN_PAGE;
		RestAssured.baseURI = Configs.getAsString("baseURI");
		this.username = username;
		this.password = password;
	}

	@When("^I submit the POST request$")
	public void submitThePostRequest() {
		Response response = RestAssured.given().contentType(ContentType.URLENC.withCharset("UTF-8"))
				.formParam("username", username).formParam("password", password).post();
		this.response = response;
	}

	@Then("^I should get 200 OK$")
	public void getStatus200OK() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Then("^Get the error page$")
	public void getTheErrorPage() throws Throwable {
		String title = null;
		try {

			BufferedReader br = new BufferedReader(new InputStreamReader(response.getBody().asInputStream()));

			String line;
			while ((line = br.readLine()) != null) {
				if (line != null && line.contains("<title>")) {
					title = line.substring(line.indexOf(">") + 1, line.lastIndexOf("<")).trim();
					break;
				}
			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(title, "ParaBank | Error");
	}

}

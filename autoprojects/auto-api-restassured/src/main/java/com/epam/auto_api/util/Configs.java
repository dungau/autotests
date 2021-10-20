package com.epam.auto_api.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Configs {
	private static Configs inst = null;
	private static Properties prop = null;

	private Configs() {
		prop = new Properties();
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("configuration.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Configs getInst() {
		if (inst == null) {
			inst = new Configs();
		}
		return inst;
	}

	private static String getProperty(String configName, String defaultValue) {
		return getInst().prop.getProperty(configName, defaultValue);
	}

	public static String getAsString(String configName) {
		return getProperty(configName, null);
	}

	public static Integer getAsInt(String configName) {
		return Integer.parseInt(getProperty(configName, "-1"));
	}
	
	public static void main(String[] args) throws UnirestException {
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.post("https://parabank.parasoft.com/parabank/login.htm")
		  .header("authority", "parabank.parasoft.com")
		  .header("cache-control", "max-age=0")
		  .header("sec-ch-ua", "\"Chromium\";v=\"94\", \"Google Chrome\";v=\"94\", \";Not A Brand\";v=\"99\"")
		  .header("sec-ch-ua-mobile", "?0")
		  .header("sec-ch-ua-platform", "\"Windows\"")
		  .header("upgrade-insecure-requests", "1")
		  .header("origin", "https://parabank.parasoft.com")
		  .header("content-type", "application/x-www-form-urlencoded")
		  .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36")
		  .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		  .header("sec-fetch-site", "same-origin")
		  .header("sec-fetch-mode", "navigate")
		  .header("sec-fetch-user", "?1")
		  .header("sec-fetch-dest", "document")
		  .header("referer", "https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC")
		  .header("accept-language", "en-US,en;q=0.9,vi;q=0.8")
		  .header("cookie", "JSESSIONID=412918C6FF2778236C0786DA7EA06B6B")
		  .field("username", "test01")
		  .field("password", "test01")
		  .asString();
		
		String location = response.getHeaders().getFirst("Location");
		int status = response.getStatus();
		
		System.out.println("test");
	}

}

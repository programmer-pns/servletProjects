package com.pns.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/getweatherinfourl")
public class WeatherInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String geoURL = "https://api.openweathermap.org/geo/1.0/direct?q=";
	String weatherURL = "https://api.openweathermap.org/data/2.5/weather?";
	String APIKEY = "fde2a1f553fac35952de1d8588804bc1";
	double lattitude = 0, longitude = 0;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		URL geoUrl = null;
		URLConnection geoConn = null;
		BufferedReader geoBR = null;
		URL weatherUrl = null;
		URLConnection weatherConn = null;
		BufferedReader weatherBR = null;
		String cityName = request.getParameter("cityname");
		if(cityName==null || cityName=="") {
			request.setAttribute("error", "emptycity");
			request.getRequestDispatcher("/errorpageurl").forward(request, response);
		}
			
		try {
			geoUrl = new URL(geoURL + cityName + "&appid=" + APIKEY);
		} catch (MalformedURLException e) {
			request.getRequestDispatcher("").forward(request, response);
		}
		if (geoUrl != null) {
			try {
				geoConn = geoUrl.openConnection();
			} catch (IOException e) {

			}
		} else {
			request.getRequestDispatcher("/errorpageurl").forward(request, response);
		}
		if (geoConn != null) {
			ObjectMapper geoOM = null;
			try {
				// create objectMapper instance
				geoOM = new ObjectMapper();

				geoOM.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

				geoBR = new BufferedReader(new InputStreamReader(geoConn.getInputStream()));

				JSONParser jp = new JSONParser();
				Object obj = null;
				obj = jp.parse(geoBR);
				JSONArray ja = (JSONArray) obj;

				JSONObject jo = null;
				for (int i = 0; i < ja.size(); i++)
						jo = (JSONObject) ja.get(i);
				if (jo != null) {
					lattitude = (double) jo.get("lat");
					longitude = (double) jo.get("lon");
					System.out.println(lattitude + " " + longitude);

					/***
					 * 
					 * Below are the code to fetch the weather api
					 * 
					 ***/

					try {
						weatherUrl = new URL(
								weatherURL + "lat=" + lattitude + "&lon=" + longitude + "&appid=" + APIKEY);
					} catch (MalformedURLException e) {
						e.printStackTrace();
						System.out.println("error");
					}
					try {
						weatherConn = weatherUrl.openConnection();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					ObjectMapper weatherOM = null;
					// create object mapper instance
					weatherOM = new ObjectMapper();

					weatherOM.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

					weatherBR = new BufferedReader(new InputStreamReader(weatherConn.getInputStream()));

					JSONParser jp2 = new JSONParser();
					Object obj2 = null;
					obj2 = jp2.parse(weatherBR);
					JSONObject weatherJO = (JSONObject) obj2;

					JSONArray ja2 = (JSONArray) weatherJO.get("weather");
					JSONObject jo2 = null;
					for (int i = 0; i < ja.size(); i++)
						jo2 = (JSONObject) ja2.get(i);
//				System.out.println("weather: "+jo2.get("main"));
					request.setAttribute("weather", jo2.get("main").toString());

					// temperature_now, min_temp, max_temp in Kelvin
					JSONObject jo3 = (JSONObject) weatherJO.get("main");
					int temp = (int) ((double) jo3.get("temp") - 273);
					int min_temp = (int) ((double) jo3.get("temp_min") - 273);
					int max_temp = (int) ((double) jo3.get("temp_max") - 273);
//			System.out.println(temp+" "+min_temp+" "+max_temp);
					request.setAttribute("max_temp", max_temp);
					request.setAttribute("min_temp", min_temp);
					request.setAttribute("temp", temp);

					// to get the country and name of the place searched
					JSONObject jo4 = (JSONObject) weatherJO.get("sys");
//			System.out.println(jo4.get("country").getClass());
//			System.out.println(weatherJO.get("name").toString().getClass());
					request.setAttribute("country", jo4.get("country").toString());
					request.setAttribute("location", weatherJO.get("name").toString());
					request.getRequestDispatcher("/displayweatherurl").forward(request, response);
				}else{
					request.setAttribute("error", "invalidcity");
					request.getRequestDispatcher("/errorpageurl").forward(request, response);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.err.println(ex.getMessage().toString());
			}
		} else {
			request.getRequestDispatcher("/errorpageurl").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
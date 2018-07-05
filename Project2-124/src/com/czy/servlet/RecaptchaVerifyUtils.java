package com.czy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
/**
 * Servlet implementation class RecaptchaVerifyUtils
 */
@WebServlet("/RecaptchaVerifyUtils")
public class RecaptchaVerifyUtils extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecaptchaVerifyUtils() {
        super();
        // TODO Auto-generated constructor stub
    }
    public static void verify( String gRecaptchaResponse) throws Exception {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
        		throw new Exception("recaptcha verification failed: gRecaptchaResponse is null or empty");
        }
        
        URL verifyUrl = new URL(SITE_VERIFY_URL);
        
        // Open Connection to URL
        HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();


        // Add Request Header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


        // Data will be sent to the server.
        String postParams = "secret=" + RecaptchaConstants.SECRET_KEY + "&response=" + gRecaptchaResponse;

        // Send Request
        conn.setDoOutput(true);
        
        // Get the output stream of Connection
        // Write data in this stream, which means to send data to Server.
        OutputStream outStream = conn.getOutputStream();
        outStream.write(postParams.getBytes());

        outStream.flush();
        outStream.close();

        // Response code return from server.
        int responseCode = conn.getResponseCode();
        System.out.println("responseCode=" + responseCode);


        // Get the InputStream from Connection to read data sent from the server.
        InputStream inputStream = conn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        
        JsonObject jsonObject = new Gson().fromJson(inputStreamReader, JsonObject.class);
        
        inputStreamReader.close();
        
        System.out.println("Response: " + jsonObject.toString());
        
        if (jsonObject.get("success").getAsBoolean()) {
        		// verification succeed
        		return;
        }
        
        throw new Exception("recaptcha verification failed: response is " + jsonObject.toString());
// 
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

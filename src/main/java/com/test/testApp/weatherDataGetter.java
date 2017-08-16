package com.test.testApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import com.test.testApp.MyUI;
import org.json.*;


public class weatherDataGetter extends MyUI {  	
	private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		    InputStream inStream = new URL(url).openStream();
		    try {
		      BufferedReader readBuff = new BufferedReader(
		    		  new InputStreamReader(inStream, Charset.forName("UTF-8")));
		      String jsonText = readAll(readBuff);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		    } finally {
		    	inStream.close();
		    }
		  }
	
	public static void doRequest() throws JSONException, IOException {
		JSONObject json = readJsonFromUrl(
				"http://apidev.accuweather.com/currentconditions/v1/294459.json?language=en&apikey=hoArfRosT1215");
		System.out.println(json.toString());
		
	}
	

}

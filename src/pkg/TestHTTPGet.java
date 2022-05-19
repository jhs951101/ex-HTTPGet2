package pkg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHTTPGet {
	
	public static void main(String[] args) throws Exception {
		
		String URL = "(URL ют╥б)";
		String TYPE = "GET";
		
		String[] Parameters = {"page=1", "perPage=5"};
		
		if(Parameters != null) {
			if(Parameters.length > 0) {
				URL += "?";
				
				for(int i=0; i<Parameters.length; i++) {
					URL += Parameters[i];
					
					if(i < Parameters.length-1)
						URL += "&";
				}
			}
		}
		
		URL url = new URL(URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod(TYPE);

		int responseCode = connection.getResponseCode();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null)  {
            stringBuffer.append(inputLine);
        }
        
        bufferedReader.close();

        String response = stringBuffer.toString();
        System.out.println(response);
    }
}
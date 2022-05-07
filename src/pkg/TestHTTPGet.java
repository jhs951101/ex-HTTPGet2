package pkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHTTPGet {
	
	public static void main(String[] args) throws IOException {
		
		String URL = "https://api.odcloud.kr/api/15093373/v1/uddi:2d2989e6-8233-40f6-a4db-dbe92e979691";
		String TYPE = "GET";
		//String USER_AGENT = "Mozilla/5.0";
		//String DATA = "test data";
		
		String[] Parameters = {"page=1", "perPage=5",
				"serviceKey=jJIP7tDlfl4QoksxclTp5r6cyl5%2Bn7WZnQUwsw3g5FXJSaXJyBRdYVyc2jEv5iuiXbwCKarsivrF63lpQxjxWA%3D%3D"};
		
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
		//connection.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = connection.getResponseCode();

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null)  {
            stringBuffer.append(inputLine);
        }
        
        bufferedReader.close();

        String response = stringBuffer.toString();
        System.out.println(response);  // JSON Array 형태로 출력
    }
}
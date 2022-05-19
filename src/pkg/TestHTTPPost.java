package pkg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestHTTPPost {
	
	public static void main(String[] args) throws Exception {
		URL url = new URL ("(URL ют╥б)");
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		
		String jsonInputString = "{\"username\":\"spg1101\", \"password\":\"1234\"}";
		
		try(OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);			
		}
		
		try(BufferedReader br = new BufferedReader(
			new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				
				while ((responseLine = br.readLine()) != null) {
				    response.append(responseLine.trim());
				}
				
				System.out.println(response.toString());
		}
    }
}
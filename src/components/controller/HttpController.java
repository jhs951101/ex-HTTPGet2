package components.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpController {
	
    public String get(String originalUrl, Map<String, String> parameters) {
    	
        String result = null;

        try {
        	String url = originalUrl;

            if (parameters != null)
            {
                boolean first = true;
                
                for (String key : parameters.keySet()) {
                	char conn = '&';

                    if (first)
                    {
                        conn = '?';
                    }

                    url += (conn + key + "=" + parameters.get(key));
                    first = false;
                }
            }
            
            URL urlObject = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseCode();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer response = new StringBuffer();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null)  {
                response.append(inputLine);
            }

            result = response.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String post(String originalUrl, Map<String, String> parameters) {
    	
        String result = null;

        try {
            int i = 0;
            String data = "";
            data += "{";

            if (parameters != null)
            {
            	for (String key : parameters.keySet()) {
                    data += String.format(" \"%s\" : \"%s\" ", key, parameters.get(key));

                    if (i != parameters.size() - 1)
                    {
                        data += ",";
                    }

                    i++;
                }
            }

            data += "}";
            
            URL url = new URL (originalUrl);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()) {
            	
            	if(parameters != null) {
                    byte[] input = data.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;

                    while ((responseLine = bufferedReader.readLine()) != null) {
                        response.append(responseLine.trim());
                    }

                    result = response.toString();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

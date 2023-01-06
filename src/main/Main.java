package main;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

import components.controller.HttpController;

public class Main {
	
	private HttpController httpController = new HttpController();
	
	public void execute() {
		
		String response = httpController.post(
			"(URL)",
			new HashMap<String, String>(){{
			    put("username", "(username)");
			    put("password", "(password)");
			}}
		);
		
		if(response == null) {
			System.out.println("Login Failed: 통신 중 오류가 발생하였습니다.");
		}
		else {
			JSONObject jObject = new JSONObject(response);
			
		    if(jObject.getBoolean("success")) {
		    	System.out.println( String.format("Login Success: %s님 환영합니다!", jObject.getString("name")) );
		    }
		    else {
		    	System.out.println("Login Failed: 아이디 또는 비밀번호가 일치하지 않습니다.");
		    }
		}
	}

	public static void main(String[] args) {
		new Main().execute();
	}

}

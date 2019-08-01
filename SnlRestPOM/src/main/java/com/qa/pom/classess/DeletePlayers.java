package com.qa.pom.classess;

import static io.restassured.RestAssured.when;


import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.response.Response;

public class DeletePlayers {
	Response r;
	JSONArray ja;
	String a;
	
	public JSONArray deletePlayers(String boardDetails,String playerDetails,int bid,int pid,String ver,String fileType) {
		a=ver;
	
		r = when().
				 get(boardDetails,a,bid,fileType);
		System.out.println("Player Id To Be Deleted : "+ pid);
		System.out.println("Players Before Deletion: "+ r.path("response.board.players"));
//		 System.out.println("Players Before Deletion: "+r.path("response.board.players.name"));
		 when().
		 	delete(playerDetails,ver,pid,fileType).
		 then().
		 	assertThat().
		 		statusCode(500);
		 r = when().
				 get(boardDetails,a,bid,fileType);
		 System.out.println("Players After Deletion: "+r.path("response.board.players"));
//		 System.out.println("Players After Deletion: "+r.path("response.board.players.name"));
		 ja = new JSONObject(r.asString()).getJSONObject("response").getJSONObject("board").getJSONArray("players");
		return ja;
	}
}

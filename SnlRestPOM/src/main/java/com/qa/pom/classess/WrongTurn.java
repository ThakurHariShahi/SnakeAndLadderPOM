package com.qa.pom.classess;

import static io.restassured.RestAssured.when;

import java.util.ArrayList;

import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;

public class WrongTurn {
	Response r, r1;
	int var, pid;
	ArrayList al;

	public void checkWrongTurn(String boardDetails, String playerMove, int bid, String ver, String fileType) {
		r = when().get(boardDetails, ver, bid, fileType);
		var = r.path("response.board.turn");
		do {
			pid = r.path("response.board.players[" + (var - 1) + "].id");
			System.out.println(pid);
			when().get(playerMove, ver, bid, fileType,pid);
			r1 = when().get(boardDetails, ver, bid, fileType);
			al = r1.path("response.board.players.position",".json");
			System.out.println(al.get(var-1));
			if(var<=3)
				var++;
			else 
				var = 1;
			Assert.assertEquals(
					(new JSONObject(r1.asString())).getJSONObject("response").getJSONObject("board").getInt("turn"), var);
		}while(!(al.get(0).toString().equals("25"))  &&  !(al.get(1).toString().equals("25"))
						 && !(al.get(2).toString().equals("25"))  && !(al.get(3).toString().equals("25")));
	}
}

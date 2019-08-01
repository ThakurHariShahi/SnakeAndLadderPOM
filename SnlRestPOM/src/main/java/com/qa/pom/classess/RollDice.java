package com.qa.pom.classess;

import static io.restassured.RestAssured.when;

import io.restassured.response.Response;

public class RollDice {
	Response r;
	int pid;
	public void doRollDice(String boardDetails,String playerMove,int bid,String ver,String fileType) {
		r=when().
				get(boardDetails,ver,bid,fileType);
		
		pid=r.path("response.board.players[0].id");
		when().
			get(playerMove,ver,bid,fileType,pid).
		then().
		    assertThat().
		    statusCode(500);
	}
}

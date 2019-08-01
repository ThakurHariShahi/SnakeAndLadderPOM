package com.qa.pom.classess;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.qa.pom.scripts.SnlRestPOM;

import io.restassured.response.Response;

public class AddNewPlayers {
	JSONObject jo1,jo2;
	ArrayList al;
	Response r;
	public ArrayList doAddNewPlayers(String boardDetails,String playerCreate,int bid,int i,int dvar,String ver,String fileType) {
		jo1 = new JSONObject();
		jo2 = new JSONObject();
		al = new ArrayList();
		do{
			if(i<4)
			{
				jo1.put("board",bid);
				jo2.put("name","hari"+dvar);
				jo1.put("player",jo2);
				given().
					body(jo1.toString()).
					post(playerCreate,ver,fileType).
				then().
					assertThat().
					statusCode(500);
				System.out.println("Player Has Been Added");
				r = when().
						get(boardDetails,ver,bid,fileType);
					al = r.path("response.board.players");
					System.out.println("Player Are :"+r.path("response.board.players["+(i)+"]"));
				i++;dvar++;
			}
			else {
				System.out.println("Maximun Players Reached");
				break;
			}
		}while(true);
		
		r = when().
			get(boardDetails,ver,bid,fileType);
		al = r.path("response.board.players");
		System.out.println("Players Are :"+r.path("response.board.players"));
//		return  new JSONObject(r.asString());
		al = r .path("response.board.players.id");
		SnlRestPOM.dvar = dvar;
		return al;
	}
}

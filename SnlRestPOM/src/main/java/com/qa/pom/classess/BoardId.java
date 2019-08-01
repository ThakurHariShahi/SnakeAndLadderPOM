package com.qa.pom.classess;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;

import java.io.FileNotFoundException;

import io.restassured.response.Response;

public class BoardId {
	Response r; 
	int bid;
	public int getBoardId(String boardCreate,String ver,String fileType ) throws FileNotFoundException, InterruptedException {

		r = when().
				get(boardCreate ,ver ,fileType);
		System.out.println("Complete Board : "+r.asString());
		
		System.out.println(r.path("response.board.id"));
		bid = r.path("response.board.id");//,fileType);
		System.out.println("Base ID  :- "+bid);
		return bid;
		
	}
}

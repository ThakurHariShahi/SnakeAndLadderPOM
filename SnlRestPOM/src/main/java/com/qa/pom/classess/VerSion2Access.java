package com.qa.pom.classess;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class VerSion2Access {
	public void auth(String boardDetails,int bid,String ver,String fileType,String userid ,String password) {
		given().
        auth().
        preemptive().
        basic(userid, password).
    when().
        get(boardDetails,ver,bid,fileType).
    then().
        assertThat().
        statusCode(200);
	}
}

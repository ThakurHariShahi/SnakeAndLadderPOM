package com.qa.pom.scripts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.dom4j.DocumentException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.esotericsoftware.yamlbeans.YamlException;
import com.qa.pom.Objects.PageObjects;

public class SnlRestPOM {
	
	PageObjects pageObject;
	String ver = "v1",
			fileType = "json",
			fileType1 = ".txt",
			fileType2 = ".yml",
		    fileType3 = ".xml",
			fileType4 = ".csv";
	String //baseUrlsFilePath = "C:\\Users\\harishahi\\eclipseoxy\\eclipse-workspace\\SnlRestPOM\\src\\test\\resources\\Resources\\TextFiles\\BaseUrls.txt",
//		   baseUrlsFilePath = (System.getProperty("user.dir")+"\\src\\test\\resources\\Resources\\TextFiles\\BaseUrls.txt"),
//	       baseUrlsFilePath = (System.getProperty("user.dir")+"\\src\\test\\resources\\Resources\\YmlFiles\\BaseUrls.yml"),
//		   baseUrlsFilePath = (System.getProperty("user.dir")+"\\src\\test\\resources\\Resources\\XmlFiles\\BaseUrls.xml"),
	       baseUrlsFilePath = (System.getProperty("user.dir")+"\\src\\test\\resources\\Resources\\CsvFiles\\BaseUrls.csv"),	
				boardCompleteDetails,boardCreate,
				boardDetails,playerCreate,playerDetails,
				playerMove,
		   //credentialsfilePath = "C:\\Users\\harishahi\\eclipseoxy\\eclipse-workspace\\SnlRestPOM\\src\\test\\resources\\Resources\\TextFiles\\Credentials.txt",
//		   credentialsfilePath = (System.getProperty("user.dir")+"\\src\\test\\resources\\Resources\\TextFiles\\credentials.txt"),
//		   credentialsfilePath = (System.getProperty("user.dir")+"\\src\\test\\resources\\Resources\\YmlFiles\\credentials.yml"),
//		   credentialsfilePath = (System.getProperty("user.dir")+"\\src\\test\\resources\\Resources\\xmlFiles\\credentials.xml"),
		   credentialsfilePath = (System.getProperty("user.dir")+"\\src\\test\\resources\\Resources\\CsvFiles\\credentials.csv"),	
				userid,password;
	
	int bid,pid,i = 0;
	public static int dvar = 0;
	JSONObject jo;
	JSONArray ja;
	ArrayList al;
	
  @Test(priority = -7)
  public void Test1() throws DocumentException, IOException {
	  
	  pageObject = new PageObjects();

	  /******************************************* END POINTS URLS *******************************************/	  
	  boardCompleteDetails = pageObject.baseUrl.getBaseUrl("baseUrls",baseUrlsFilePath, "boardCompleteDetails",fileType4);
	  boardCreate = pageObject.baseUrl.getBaseUrl("baseUrls",baseUrlsFilePath, "boardCreate",fileType4);
	  //System.out.println("/**************"+boardCreate);
	  boardDetails = pageObject.baseUrl.getBaseUrl("baseUrls",baseUrlsFilePath, "boardDetails",fileType4);
	  System.out.println("/**************"+boardDetails);
	  playerCreate = pageObject.baseUrl.getBaseUrl("baseUrls",baseUrlsFilePath, "playerCreate",fileType4);
	  playerDetails = pageObject.baseUrl.getBaseUrl("baseUrls",baseUrlsFilePath, "playerDetails",fileType4);
	  playerMove = pageObject.baseUrl.getBaseUrl("baseUrls",baseUrlsFilePath, "playerMove",fileType4);
	  
	  userid = pageObject.baseUrl.getBaseUrl("credentials",credentialsfilePath, "userid",fileType4);
	  password = pageObject.baseUrl.getBaseUrl("credentials",credentialsfilePath, "password",fileType4);
	  
	  /******************************************* Test Cases *******************************************/	  
//	  bid = pageObject.bid.getBoardId(boardCreate, ver, fileType);
//	  
//	  o = pageObject.addNewPlayer.doAddNewPlayers(boardDetails, playerCreate, bid, i, ver, fileType);
//	  i=o.length;
//	  
//	  pageObject.checkForValidNoOfPlayer.checkForValidNoOfPlayers(boardDetails, bid, ver, fileType);
//	  
//	  pid = (Integer)o[0].get("id");
//	  pageObject.deletePlayer.deletePlayers(boardDetails, bid, pid, ver, fileType);
//	  i=3;
//	  
//	  pageObject.rollDice.doRollDice(boardDetails, playerMove, bid, ver, fileType);
//	  pageObject.verSion2Access.auth(boardDetails, bid, ver, fileType, userid, password);
//	  pageObject.wrongTurn.checkWrongTurn(boardDetails, playerMove, bid, ver, fileType);
	  
	  
  }
  @Test(priority = -6)
  public void Test2() throws FileNotFoundException, InterruptedException {
	  System.out.println("\n GETBOARDID");;
	  bid = pageObject.bid.getBoardId(boardCreate, ver, fileType);
  }
  @Test(priority = -5)
  public void Test3() {
	  System.out.println("\n AddNewPlayers");;
	  al = pageObject.addNewPlayer.doAddNewPlayers(boardDetails, playerCreate, bid, i,dvar, ver, fileType);
	  i=al.size();
	
  }
  @Test(priority = -4)
  public void Test4() {
	  System.out.println("\n ValidNoOfPlayers");
	  pageObject.checkForValidNoOfPlayer.checkForValidNoOfPlayers(boardDetails, bid, ver, fileType);
  }
  @Test(priority = -3)
  public void Test5() {
	  System.out.println("\n deletePlayers");;
		if (i >= 1) {
//			pid = (Integer) ((JSONObject) jo.getJSONObject("response").getJSONObject("board").getJSONArray("players")
//					.get(0)).get("id");
			pid = (Integer) al.get(0);
			ja = pageObject.deletePlayer.deletePlayers(boardDetails, playerDetails, bid, pid, ver, fileType);
			i = ja.length();
		}
		else {
			System.out.println("No players To delete");
		}
  }
  @Test(priority =-2)
  public void Test6() throws FileNotFoundException {
	  System.out.println("\n AddNewPlayers");;
	  al = pageObject.addNewPlayer.doAddNewPlayers(boardDetails, playerCreate, bid, i, dvar, ver, fileType);
	 
  }
  @Test(priority = -1)
  public void Test7() {
	  System.out.println("\n RollDice");;
	  pageObject.rollDice.doRollDice(boardDetails, playerMove, bid, ver, fileType);

  }
  @Test(priority = 0)
  public void Test8() {
	  pageObject.verSion2Access.auth(boardDetails, bid, ver, fileType, userid, password);

  }
  @Test(priority = 5)
  public void Test9() {
	  System.out.println("\n checkWrongTurn");;
	  pageObject.wrongTurn.checkWrongTurn(boardDetails, playerMove, bid, ver, fileType);

  }
}

package com.qa.pom.classess;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;



public class BoardStruct {
		
	public void init(UUID uuid) {
        JSONArray ja = new JSONArray();
        for(int a = 1; a <=100; a++){
            ja.put(a, defSteps(a,a));
        }
        
        // ladders
        ja.put(2, defSteps(2,24));
        ja.put(11, defSteps(11, 33));
        ja.put(25, defSteps(25, 85));
        ja.put(37, defSteps(37, 61));
        ja.put(68, defSteps(68, 90));
        ja.put(79, defSteps(79, 97));
        // snakes
        ja.put(99, defSteps(99, 3));
        ja.put(93, defSteps(93, 67));
        ja.put(55, defSteps(55, 13));
        ja.put(70, defSteps(70, 32));
        ja.put(23, defSteps(23, 7));
        
        
        JSONObject jo = new JSONObject();
        jo.put("players", new JSONArray());
        jo.put("turn", 0);
        jo.put("steps", ja);
        
//        PrintWriter writer = new PrintWriter(uuid.toString() + ".board", "UTF-8");
//        writer.println(data.toString(2));
//        writer.close();
    }
	
	    public JSONObject defSteps(int SourcePos,int DesPos)
		{
			return(  new JSONObject ("{\"Source\":"+SourcePos+",\"Des\":"+DesPos+"}")  );
		}
		
		
}

package com;
import model.Creator; 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
import com.google.gson.*; 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/creator") 
public class CreatorServlet {
	
	Creator creatorObj = new Creator();
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readItems() 
 {     //test
 return creatorObj.displayCreator(); 
 } 

@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertCreator(@FormParam("fullname") String name, 
 @FormParam("city") String city, 
 @FormParam("contactnum") String contactnum, 
 @FormParam("email") String email,
 @FormParam("fieldofinterest") String fieldofinterest, 
 @FormParam("currentbudget") String budget)

{ 
 String output = creatorObj.insertCreator(name, city, contactnum, email,fieldofinterest,budget); 
return output; 
}

@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updateCreator(String creatorinfo) 
{ 

 JsonObject creatorObject = new JsonParser().parse(creatorinfo).getAsJsonObject(); 
 
 String creatorid = creatorObject.get("projectid").getAsString(); 
 String name = creatorObject.get("name").getAsString(); 
 String city = creatorObject.get("category").getAsString(); 
 String contactnum = creatorObject.get("estcost").getAsString(); 
 String email = creatorObject.get("esttime").getAsString(); 
 String fieldofinterst =creatorObject.get("esttime").getAsString(); 
 String budget = creatorObject.get("esttime").getAsString(); 
 String output = creatorObj.updateCreator(creatorid, name, city, contactnum, email, fieldofinterst, budget); 
return output;
} //test

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteCreator(String creatorinfo) 
{ 

 Document doc = Jsoup.parse(creatorinfo, "", Parser.xmlParser()); 
 
 String creatorid = doc.select("creatorid").text(); 
 String output = creatorObj.deleteCreator(creatorid); 
return output; 
}


}
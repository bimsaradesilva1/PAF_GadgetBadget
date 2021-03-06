package com;

//For REST Service
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.research_IT19216256;

@Path("/Research")

public class researchServices_IT19216256 {

	research_IT19216256 RObj = new research_IT19216256();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearch() {
		return RObj.readResearch();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch(@FormParam("researchName") String researchName,
			@FormParam("researchDescription") String researchDescription,
			@FormParam("researchPrice") String researchPrice, @FormParam("researchDate") String researchDate) {
		String output = RObj.insertResearch(researchName, researchDescription, researchPrice, researchDate);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearch(String researchData) {
		// Convert the input string to a JSON object
		JsonObject RObject = new JsonParser().parse(researchData).getAsJsonObject();
		// Read the values from the JSON object
		String researchID = RObject.get("researchID").getAsString();
		String researchName = RObject.get("researchName").getAsString();
		String researchDescription = RObject.get("researchDescription").getAsString();
		String researchPrice = RObject.get("researchPrice").getAsString();
		String researchDate = RObject.get("researchDate").getAsString();
		String output = RObj.updateResearch(researchID, researchName, researchDescription, researchPrice, researchDate);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearch(String researchData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(researchData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String researchID = doc.select("researchID").text();
		String output = RObj.deleteResearch(researchID);
		return output;
	}

}

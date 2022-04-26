package com;

import model.Inquiry;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Inquiry")
public class Inquiryservice {
	Inquiry InquiryObj = new Inquiry();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInquiry() {
		return InquiryObj.readInquiry();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInquiry(@FormParam("name") String name, 
			@FormParam("date") String date,
			@FormParam("note") String note) {
		String output = InquiryObj.insertInquiry(name, date, note);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateInquiry(String InquiryData) {
		// Convert the input string to a JSON object
		JsonObject ProObject = new JsonParser().parse(InquiryData).getAsJsonObject();

		// Read the values from the JSON object
		String Id = ProObject.get("id").getAsString();
		String name = ProObject.get("name").getAsString();
		String date = ProObject.get("date").getAsString();
		String note = ProObject.get("note").getAsString();

		String output = InquiryObj.updateInquiry(Id, name, date, note);
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiry(String InquiryData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(InquiryData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String Id = doc.select("id").text();
		String output = InquiryObj.deleteInquiry(Id);
		return output;
	}
}

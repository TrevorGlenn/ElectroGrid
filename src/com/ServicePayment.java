package com;

import model.Payment;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class ServicePayment {
	Payment PaymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("name") String Name, 
			@FormParam("amount") String amount,
			@FormParam("description") String description) {
		String output = PaymentObj.insertPayment(Name, amount, description);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String PaymentData) {
		// Convert the input string to a JSON object
		JsonObject ProObject = new JsonParser().parse(PaymentData).getAsJsonObject();

		// Read the values from the JSON object
		String Id = ProObject.get("id").getAsString();
		String name = ProObject.get("name").getAsString();
		String amount = ProObject.get("amount").getAsString();
		String description = ProObject.get("description").getAsString();

		String output = PaymentObj.updatePayment(Id, name, amount, description);
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String PaymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(PaymentData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String Id = doc.select("id").text();
		String output = PaymentObj.deletePayment(Id);
		return output;
	}
}

package com;

import model.Bill;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Bill")
public class Billservice {
	Bill BillObj = new Bill();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBill() {
		return BillObj.readBill();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(@FormParam("bname") String bName, 
			@FormParam("price") String bPrice,
			@FormParam("bdes") String bDes) {
		String output = BillObj.insertBill(bName, bPrice, bDes);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateBill(String BillData) {
		// Convert the input string to a JSON object
		JsonObject ProObject = new JsonParser().parse(BillData).getAsJsonObject();

		// Read the values from the JSON object
		String Id = ProObject.get("id").getAsString();
		String bname = ProObject.get("bname").getAsString();
		String price = ProObject.get("price").getAsString();
		String bdes = ProObject.get("bdes").getAsString();

		String output = BillObj.updateBill(Id, bname, price, bdes);
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String BillData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(BillData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String pId = doc.select("id").text();
		String output = BillObj.deleteBill(pId);
		return output;
	}
}

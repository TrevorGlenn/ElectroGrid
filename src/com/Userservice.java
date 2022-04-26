package com;

import model.User;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class Userservice {
	User UserObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return UserObj.readUser();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("uname") String uName, 
			@FormParam("age") String age,
			@FormParam("address") String add) {
		String output = UserObj.insertUser(uName, age, add);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateUser(String UserData) {
		// Convert the input string to a JSON object
		JsonObject ProObject = new JsonParser().parse(UserData).getAsJsonObject();

		// Read the values from the JSON object
		String Id = ProObject.get("id").getAsString();
		String uname = ProObject.get("uname").getAsString();
		String age = ProObject.get("age").getAsString();
		String add = ProObject.get("address").getAsString();

		String output = UserObj.updateUser(Id, uname, age, add);
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String UserData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(UserData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String Id = doc.select("id").text();
		String output = UserObj.deleteUser(Id);
		return output;
	}
}

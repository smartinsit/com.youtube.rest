package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.OracleDB;
//import com.youtube.dao.Schema308tube;
import com.youtube.util.ToJSON_V1_1;;

@Path("/v1_1/inventory/")
public class V1_1_inventory {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		String returnString = null;
		Response rb = null;
		
		try {
			conn = OracleDB.OracleDBConn().getConnection();
			query = conn.prepareStatement("select * from pc_parts");
			
			// define the Oracle cursor
			ResultSet rs = query.executeQuery();
			
			ToJSON_V1_1 converter = new ToJSON_V1_1();
			
			JSONArray json = new JSONArray();
			json = converter.toJSONArray(rs);
			query.close();
			
			returnString = json.toString();
			rb = Response.ok(returnString).build();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) conn.close();
		}
		return rb;
	}
	
}

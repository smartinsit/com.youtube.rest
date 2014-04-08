package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.OracleDB;
//import com.youtube.dao.Schema308tube;
import com.youtube.util.ToJSON;;

@Path("/v1/inventory/")
public class V1_inventory {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnAllPcParts() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		String returnString = null;
		
		try {
			conn = OracleDB.OracleDBConn().getConnection();
			query = conn.prepareStatement("select * from pc_parts");
			
			// define the Oracle cursor
			ResultSet rs = query.executeQuery();
			
			ToJSON converter = new ToJSON();
			
			JSONArray json = new JSONArray();
			json = converter.toJSONArray(rs);
			query.close();
			
			returnString = json.toString();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) conn.close();
		}
		return returnString;
	}
	
}

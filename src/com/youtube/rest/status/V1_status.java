package com.youtube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.*;
import com.youtube.dao.*;

@Path("/v1/status/")
public class V1_status {

	private static final String api_version = "00.01.00";
	/**
	 * This method sits at the root of the api.  It will return the name
	 * of this api.
	 * 
	 * @return String - Title of the api
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p> Java Web Services </p>";
	}

	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root.  You will need to add version
	 * into the URL path.
	 * 
	 * Example:
	 * http://localhost:7001/com.youtube.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	@GET
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p> Version: </p>" + api_version;
	}
	
	/**
	 * This method will connect to the oracle database and return the date/time stamp.
	 * It will then return the date/time to the user in String format
	 * 
	 * This was explained in Part 3 of the Java Rest Tutorial Series on YouTube
	 * 
	 * Pre-episode 6, updated because Oracle308tube.java no longer accessible.
	 * 
	 * @return String -  returns the database date/time stamp
	 * @throws Exception
	 */
	@Path("/database")
	@GET
	@Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try {
			conn = OracleDB.OracleDBConn().getConnection();
			query = conn.prepareStatement("select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME from sys.dual");
			ResultSet rs = query.executeQuery();
			
			while (rs.next()) {
				// /*debug*/ System.out.println(rs.getString("DATETIME");
				myString = rs.getString("DATETIME");
				
			}
			
			query.close(); // Close connection
			
			returnString = "<p>Database status</p> " +
					"<p> Database date/time return: " + myString + "</p>";
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// will close the connection no matter what happened in my program
			// Even if there was an error and the exception was called
			if (conn != null) conn.close();
		}
		return returnString;
	}
}

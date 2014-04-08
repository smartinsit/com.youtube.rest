package com.youtube.dao;

import javax.naming.*; 
import javax.sql.*; 


public class OracleDB {
	
	/* Both these variables are define private so
	 * nobody outside this class can manipulate their values
	 * private static {object} {name} = {value};
	 */
	private static DataSource OracleDb = null;
	private static Context context = null;
	
	/* define a public method so other methods can access it
	 * and change the data
	 */
	public static DataSource OracleDBConn() throws Exception {
		
		if (OracleDb != null) {
			return OracleDb;
		}
		
		try {
			if (context == null) {
				context = new InitialContext();
			}
			OracleDb = (DataSource) context.lookup("oracledb");
			
		}
		catch (Exception e) {
			e.printStackTrace(); 
		}
		return OracleDb;
	}

}

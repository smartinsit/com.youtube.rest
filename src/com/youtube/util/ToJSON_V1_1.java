package com.youtube.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;

import org.owasp.esapi.ESAPI;

public class ToJSON_V1_1 {

	public JSONArray toJSONArray(ResultSet rs) throws Exception {
		JSONArray json = new JSONArray();
		
		String temp = null;		

		try {
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				// find number of columns
				int numColumns = rsmd.getColumnCount();
				// Each row in the ResultSet wil be converted to JSON
				JSONObject obj = new JSONObject();

				// Loop though all the columns and put them in a JSON oject
				for (int i = 1; i <= numColumns; i++) {

					String column_name = rsmd.getColumnName(i);

					if (rsmd.getColumnType(i) == java.sql.Types.ARRAY) {
						obj.put(column_name, rs.getArray(column_name));
						/* Debug */System.out.println("toJson: ARRAY");
					} else if (rsmd.getColumnType(i) == java.sql.Types.BIGINT) {
						obj.put(column_name, rs.getInt(column_name));
						/* Debug */System.out.println("toJson: BIGINT");
					} else if (rsmd.getColumnType(i) == java.sql.Types.BOOLEAN) {
						obj.put(column_name, rs.getBoolean(column_name));
						/* Debug */System.out.println("toJson: BOOLEAN");
					} else if (rsmd.getColumnType(i) == java.sql.Types.BLOB) {
						obj.put(column_name, rs.getBlob(column_name));
						/* Debug */System.out.println("toJson: BLOB");
					} else if (rsmd.getColumnType(i) == java.sql.Types.DOUBLE) {
						obj.put(column_name, rs.getDouble(column_name));
						/* Debug */System.out.println("toJson: DOUBLE");
					} else if (rsmd.getColumnType(i) == java.sql.Types.FLOAT) {
						obj.put(column_name, rs.getFloat(column_name));
						/* Debug */System.out.println("toJson: FLOT");
					} else if (rsmd.getColumnType(i) == java.sql.Types.INTEGER) {
						obj.put(column_name, rs.getInt(column_name));
						/* Debug */System.out.println("toJson: INTEGER");
					} else if (rsmd.getColumnType(i) == java.sql.Types.NVARCHAR) {
						obj.put(column_name, rs.getNString(column_name));
						/* Debug */System.out.println("toJson: NVARCHAR");
					} else if (rsmd.getColumnType(i) == java.sql.Types.VARCHAR) {
						// convert column_name to string and load into temp
						temp = rs.getString(column_name);
						// this will un-encode the content
						temp = ESAPI.encoder().canonicalize(temp);
						// this will encode into HTML
						temp = ESAPI.encoder().encodeForHTML(temp);
						obj.put(column_name, temp);
						// obj.put(column_name, rs.getString(column_name));						
						/* Debug */System.out.println("toJson: VARCHAR");
					} else if (rsmd.getColumnType(i) == java.sql.Types.TINYINT) {
						obj.put(column_name, rs.getInt(column_name));
						/* Debug */System.out.println("toJson: TINYINT");
					} else if (rsmd.getColumnType(i) == java.sql.Types.SMALLINT) {
						obj.put(column_name, rs.getInt(column_name));
						/* Debug */System.out.println("toJson: SMALLINT");
					} else if (rsmd.getColumnType(i) == java.sql.Types.DATE) {
						obj.put(column_name, rs.getDate(column_name));
						/* Debug */System.out.println("toJson: DATE");
					} else if (rsmd.getColumnType(i) == java.sql.Types.TIMESTAMP) {
						obj.put(column_name, rs.getTimestamp(column_name));
						/* Debug */System.out.println("toJson: TIMESTAMP");
					} else if (rsmd.getColumnType(i) == java.sql.Types.NUMERIC) {
						obj.put(column_name, rs.getBigDecimal(column_name));
						/* Debug */System.out.println("toJson: NUMERIC");
					} else {
						obj.put(column_name, rs.getObject(column_name));
						/* Debug */System.out.println("toJson: Object "
								+ column_name);
					}
				} // end foreach
				json.put(obj);
			} // end while

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json; // return Json array
	}
}

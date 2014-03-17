package com.youtube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1/status/")
public class V1_status {

	private static final String api_version = "00.01.00";
	
	/** 
	 * This method sits at the root of the api. It will return the name of the
	 * api.
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p> Java Web Services </p>";
	}

	/**
	 * This method is nested down on to the root. It will return the version for 
	 * the api.
	 * @return
	 */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p> Version: </p>" + api_version;
	}

}

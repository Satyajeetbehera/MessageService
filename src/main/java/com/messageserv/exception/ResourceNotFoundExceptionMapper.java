package com.messageserv.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.messageserv.model.ErrorInfo;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {

	@Override
	public Response toResponse(ResourceNotFoundException exc) {
		// TODO Auto-generated method stub
		ErrorInfo rrrorInfo = new ErrorInfo(exc.getMessage(), 404, "Resource does not exist");
		return Response.status(Status.NOT_FOUND)
				.entity(rrrorInfo)
				.build();
	}

	
}

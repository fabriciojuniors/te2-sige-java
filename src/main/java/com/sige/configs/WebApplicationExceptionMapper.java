package com.sige.configs;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author fabri
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException>{

    @Override
    public Response toResponse(WebApplicationException e) {
        int status = e.getResponse().getStatus();
        String message = e.getMessage();
        
        return Response.status(status).entity(new WebApplicationResponseModel(message)).build();
    }
    
}

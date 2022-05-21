/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.controllers;

import com.sige.models.Local;
import com.sige.services.LocalService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author fabri
 */

@Path("/local")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LocalController {
    
    @Inject
    LocalService service;
    
    @GET
    public List<Local> findAll(){
        return service.findAll();
    }
    
    @GET
    @Path("/{id}")
    public Local findById(@PathParam("id") long id){
        
        Local local = service.findById(id);
        
        if(local == null){
            throw new WebApplicationException("Local não encontrado com ID " + id);
        }
        
        return local;
        
    }
    
    @GET
    @Path("/busca/{nome}")
    public List<Local> findByDescricao(@PathParam("nome") String nome){
        return service.findByNome(nome);
    }
    
    @POST
    public Local save(Local local){
        return service.save(local);
    }
    
    @PUT
    @Path("/{id}")
    public Local update(@PathParam("id") long id, Local local){
        return service.update(id, local);
    }
}

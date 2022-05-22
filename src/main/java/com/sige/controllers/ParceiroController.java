/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.controllers;

import com.sige.models.Parceiro;
import com.sige.services.ParceiroService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

@Path("/parceiro")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParceiroController {
    
    @Inject
    ParceiroService service;
    
    @GET
    public List<Parceiro> findAll(){
        return service.findAll();
    }
    
    @GET
    @Path("/{id}")
    public Parceiro findById(@PathParam("id") long id){
        
        Parceiro parceiro = service.findById(id);
        
        if(parceiro == null){
            throw new WebApplicationException("Parceiro não encontrado com ID " + id);
        }
        
        return parceiro;
        
    }
    
    @GET
    @Path("/busca/{nome}")
    public List<Parceiro> findByDescricao(@PathParam("nome") String nome){
        return service.findByNome(nome);
    }
    
    @POST
    public Parceiro save(Parceiro parceiro){
        return service.save(parceiro);
    }
    
    @PUT
    @Path("/{id}")
    public Parceiro update(@PathParam("id") long id, Parceiro parceiro){
        return service.update(id, parceiro);
    }
    
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id){
        service.delete(id);
    }
    
}

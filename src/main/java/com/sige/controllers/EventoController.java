/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.controllers;

import com.sige.models.Evento;
import com.sige.services.EventoService;
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

@Path("/evento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventoController {

    @Inject
    EventoService service;

    @GET
    public List<Evento> findAll(){
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Evento findById(@PathParam("id") long id){

        Evento evento = service.findById(id);

        if(evento == null){
            throw new WebApplicationException("Evento não encontrado com ID " + id);
        }

        return evento;

    }

    @GET
    @Path("/busca/{nome}")
    public List<Evento> findByDescricao(@PathParam("nome") String nome){
        return service.findByNome(nome);
    }

    @POST
    public Evento save(Evento evento){
        return service.save(evento);
    }

    @PUT
    @Path("/{id}")
    public Evento update(@PathParam("id") long id, Evento evento){
        return service.update(id, evento);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id){
        service.delete(id);
    }

}

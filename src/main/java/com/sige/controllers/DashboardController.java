/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.controllers;

import com.sige.models.DashboardLocalXEvento;
import com.sige.models.DashboardParceiroXEvento;
import com.sige.services.DashboardService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author fabri
 */
@Path("/dashboard")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DashboardController {
    
    @Inject
    DashboardService service;
    
    @GET
    @Path("local")
    public List<DashboardLocalXEvento> findByLocal(){
        return service.findByLocal();
    }
    
    @GET
    @Path("parceiro")
    public List<DashboardParceiroXEvento> findByParceiro(){
        return service.findByParceiro();
    }
    
}

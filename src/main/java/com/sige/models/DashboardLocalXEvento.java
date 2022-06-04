/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.models;

/**
 *
 * @author fabri
 */
public class DashboardLocalXEvento {
    
    public String local;
    public long quantidade;

    public DashboardLocalXEvento(long quantidade, Local local) {
        this.local = local.getNome();
        this.quantidade = quantidade;
    }

    public DashboardLocalXEvento() {
    }
    
    
    
}

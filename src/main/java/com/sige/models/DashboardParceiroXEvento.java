/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.models;

/**
 *
 * @author fabri
 */
public class DashboardParceiroXEvento {
    
    public String parceiro;
    public long quantidade;

    public DashboardParceiroXEvento( long quantidade, Parceiro parceiro) {
        this.parceiro = parceiro.getNomeFantasia();
        this.quantidade = quantidade;
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.enums;

/**
 *
 * @author fabri
 */
public enum Status {
    
    PENDENTE("PENDENTE"),
    APROVADO("APROVADO"),
    REPROVADO("REPROVADO"),
    FINALIZADO("FINALIZADO");
    
    private String descricao;
    
    private Status(String descricao){
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    
}

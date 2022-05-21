/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.enums;

/**
 *
 * @author fabri
 */
public enum Estados {
    
    SC("Santa Catarina"),
    PR("Paraná"),
    RS("Rio Grande do Sul");
    
    private String descricao;

    private Estados(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    
    
    
}

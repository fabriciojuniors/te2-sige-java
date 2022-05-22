/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.enums;

/**
 *
 * @author fabri
 */

public enum ClassificacaoIndicativa {
    LIVRE("Livre"),
    DEZOITO("18"),
    DEZESSEIS("16"),
    QUATORZE("14"),
    DOZE("12"),
    DEZ("10");
    
    private String descricao;

    private ClassificacaoIndicativa(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    
}

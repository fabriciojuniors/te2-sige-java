/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.services;

import com.sige.models.Endereco;
import com.sige.models.Parceiro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author fabri
 */

@Stateless
public class ParceiroService {
    
    @PersistenceContext(unitName = "SigePU")
    private EntityManager entityManager;
    
    public List<Parceiro> findAll(){
        return entityManager.createQuery("SELECT p FROM Parceiro p", Parceiro.class).getResultList();
    }
    
    public Parceiro findById(long id){
        return entityManager.find(Parceiro.class, id);
    }
    
    public List<Parceiro> findByNome(String nome){
        return entityManager.createQuery("SELECT p FROM Parceiro p WHERE nomeFantasia like :nome or razaoSocial like :nome")
                .setParameter("nome", "%"+ nome +"%")
                .getResultList();
    }
    
    public Parceiro save(Parceiro parceiro){
        
        Endereco endereco = parceiro.getEndereco();
        entityManager.persist(endereco);
        
        parceiro.setEndereco(endereco);        
        entityManager.persist(parceiro);
        
        return parceiro;
    }
    
    public Parceiro update(long id, Parceiro parceiro){
        
        Endereco endereco = parceiro.getEndereco();
        entityManager.merge(endereco);
        
        parceiro.setId(id);
        parceiro.setEndereco(endereco);
        
        entityManager.merge(parceiro);
        
        return parceiro;
    }
    
    public void delete(long id){
        Parceiro parceiro = findById(id);
        
        if(parceiro == null){
            throw new WebApplicationException("Parceiro não encontrado com ID " + id);
        }
                
        entityManager.remove(parceiro);
    }
    
}

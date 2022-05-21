/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.services;

import com.sige.models.Endereco;
import com.sige.models.Local;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fabri
 */

@Stateless
public class LocalService {
    
    @PersistenceContext(unitName = "SigePU")
    private EntityManager entityManager;
    
    public List<Local> findAll(){
        return entityManager.createQuery("SELECT l FROM Local l", Local.class).getResultList();
    }
    
    public Local findById(long id){
        return entityManager.find(Local.class, id);
    }
    
    public List<Local> findByNome(String nome){
        return entityManager.createQuery("SELECT l FROM Local l WHERE nome like :nome")
                .setParameter("nome", "%"+ nome +"%")
                .getResultList();
    }
    
    public Local save(Local local){
        
        Endereco endereco = local.getEndereco();
        entityManager.persist(endereco);
        
        local.setEndereco(endereco);        
        entityManager.persist(local);
        
        return local;
    }
    
    public Local update(long id, Local local){
        
        Endereco endereco = local.getEndereco();
        entityManager.merge(endereco);
        
        local.setId(id);
        local.setEndereco(endereco);
        
        entityManager.merge(local);
        
        return local;
    }
   
}

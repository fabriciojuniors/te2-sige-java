/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.services;

import com.sige.models.Endereco;
import com.sige.models.Evento;
import com.sige.models.Local;
import com.sige.models.Parceiro;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author fabri
 */
@Stateless
public class EventoService {

    @PersistenceContext(unitName = "SigePU")
    private EntityManager entityManager;
    
    @Inject
    LocalService localService;
    
    @Inject
    ParceiroService parceiroService;

    public List<Evento> findAll() {
        return entityManager.createQuery("SELECT e FROM Evento e", Evento.class).getResultList();
    }

    public Evento findById(long id) {
        return entityManager.find(Evento.class, id);
    }

    public List<Evento> findByNome(String nome) {
        return entityManager.createQuery("SELECT e FROM Evento e WHERE nome like :nome")
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    public Evento save(Evento evento) {

        Local local = localService.findById(evento.getLocal().getId());
        Parceiro parceiro = parceiroService.findById(evento.getParceiro().getId());
        
        evento.setLocal(local);
        evento.setParceiro(parceiro);
        
        entityManager.persist(evento);

        return evento;
    }

    public Evento update(long id, Evento evento) {
        entityManager.merge(evento);

        return evento;
    }

    public void delete(long id) {
        Evento evento = findById(id);

        if (evento == null) {
            throw new WebApplicationException("Evento não encontrado com ID " + id);
        }

        entityManager.remove(evento);
    }

}

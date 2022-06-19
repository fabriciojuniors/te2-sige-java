/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.services;

import com.sige.enums.Status;
import com.sige.models.Evento;
import com.sige.models.Local;
import com.sige.models.Parceiro;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

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

    public Boolean hasDisponibilidadeLocal(Local local, LocalDateTime dataHora) {
        return entityManager.createQuery("SELECT e FROM Evento e WHERE e.local.id = :local and e.dataHora = :dataHora")
                .setParameter("local", local.getId())
                .setParameter("dataHora", dataHora)
                .getResultList().isEmpty();
    }

    public Boolean hasDisponibilidadeLocal(Local local, LocalDateTime dataHora, long id) {
        return entityManager.createQuery("SELECT e FROM Evento e WHERE e.local.id = :local and e.dataHora = :dataHora and e.id <> :id")
                .setParameter("local", local.getId())
                .setParameter("dataHora", dataHora)
                .setParameter("id", id)
                .getResultList().isEmpty();
    }

    public Evento save(Evento evento) {

        Local local = localService.findById(evento.getLocal().getId());
        Parceiro parceiro = parceiroService.findById(evento.getParceiro().getId());

        evento.setLocal(local);
        evento.setParceiro(parceiro);

        valida(evento, null);

        entityManager.persist(evento);

        return evento;
    }

    public Evento update(long id, Evento evento) {
        evento.setId(id);

        Local local = localService.findById(evento.getLocal().getId());
        Parceiro parceiro = parceiroService.findById(evento.getParceiro().getId());
        Evento savedEvento = findById(id);

        evento.setLocal(local);
        evento.setParceiro(parceiro);

        valida(evento, savedEvento);
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

    private void valida(Evento evento, Evento savedEvento) {
        List<String> msgs = new ArrayList<>();

        if (evento.getId() > 0) {
            if (!hasDisponibilidadeLocal(evento.getLocal(), evento.getDataHora(), evento.getId())) {
                throw new WebApplicationException("O local " + evento.getLocal().getNome() + " não possui disponibilidade para a data desejada.", Response.Status.BAD_REQUEST);
            }
            
            if(!savedEvento.getDataHora().equals(evento.getDataHora()) && LocalDateTime.now().isAfter(evento.getDataHora())){
                throw new WebApplicationException("A data de realização do evento não pode ser retroativa", Response.Status.BAD_REQUEST);
            }
        } else {
            if (!hasDisponibilidadeLocal(evento.getLocal(), evento.getDataHora())) {
                throw new WebApplicationException("O local " + evento.getLocal().getNome() + " não possui disponibilidade para a data desejada.", Response.Status.BAD_REQUEST);
            }

            if (evento.getStatus().equals(Status.FINALIZADO) || evento.getStatus().equals(Status.REPROVADO)) {
                throw new WebApplicationException("Não é permitido o cadastro de eventos com status Finalizado ou Reprovado.", Response.Status.BAD_REQUEST);
            }

            if (LocalDateTime.now().isAfter(evento.getDataHora())) {
                throw new WebApplicationException("A data de realização do evento não pode ser retroativa", Response.Status.BAD_REQUEST);
            }
        }

    }
}

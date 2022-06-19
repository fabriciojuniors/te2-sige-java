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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author fabri
 */
@Stateless
public class LocalService {

    @PersistenceContext(unitName = "SigePU")
    private EntityManager entityManager;

    public List<Local> findAll() {
        return entityManager.createQuery("SELECT l FROM Local l", Local.class).getResultList();
    }

    public Local findById(long id) {
        return entityManager.find(Local.class, id);
    }

    public List<Local> findByNome(String nome) {
        return entityManager.createQuery("SELECT l FROM Local l WHERE nome like :nome")
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    private boolean existsByNome(String nome) {
        return !entityManager.createQuery("SELECT l FROM Local l WHERE nome = :nome")
                .setParameter("nome", nome)
                .getResultList()
                .isEmpty();
    }

    private boolean existsByNome(String nome, long id) {
        return entityManager.createQuery("SELECT l FROM Local l WHERE nome = :nome and id <> :id")
                .setParameter("nome", nome)
                .setParameter("id", id)
                .getResultList()
                .isEmpty();
    }

    public Local save(Local local) {

        Endereco endereco = local.getEndereco();
        entityManager.persist(endereco);

        local.setEndereco(endereco);

        valida(local);

        entityManager.persist(local);

        return local;
    }

    public Local update(long id, Local local) {
        local.setId(id);

        valida(local);

        Endereco endereco = local.getEndereco();
        entityManager.merge(endereco);

        local.setEndereco(endereco);

        entityManager.merge(local);

        return local;
    }

    public void delete(long id) {
        Local local = findById(id);

        if (local == null) {
            throw new WebApplicationException("Local não encontrado com ID " + id);
        }

        entityManager.remove(local);
    }

    private void valida(Local local) {

        if (local.getId() > 0) {
            if (!existsByNome(local.getNome(), local.getId())) {
                throw new WebApplicationException("O local " + local.getNome() + " já possui cadastro");
            }
        } else {
            if (existsByNome(local.getNome())) {
                throw new WebApplicationException("O local " + local.getNome() + " já possui cadastro");
            }
        }

    }
}

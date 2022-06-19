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

    public List<Parceiro> findAll() {
        return entityManager.createQuery("SELECT p FROM Parceiro p", Parceiro.class).getResultList();
    }

    public Parceiro findById(long id) {
        return entityManager.find(Parceiro.class, id);
    }

    public List<Parceiro> findByNome(String nome) {
        return entityManager.createQuery("SELECT p FROM Parceiro p WHERE nomeFantasia like :nome or razaoSocial like :nome")
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    private boolean existsByNome(String nome) {
        return !entityManager.createQuery("SELECT p FROM Parceiro p WHERE razaoSocial = :nome")
                .setParameter("nome", nome)
                .getResultList()
                .isEmpty();
    }

    private boolean existsByNome(String nome, long id) {
        return !entityManager.createQuery("SELECT p FROM Parceiro p WHERE razaoSocial = :nome and id <> :id")
                .setParameter("nome", nome)
                .setParameter("id", id)
                .getResultList()
                .isEmpty();
    }

    private boolean existsByCNPJ(String CNPJ) {
        return !entityManager.createQuery("SELECT p FROM Parceiro p WHERE CNPJ = :CNPJ")
                .setParameter("CNPJ", CNPJ)
                .getResultList()
                .isEmpty();
    }

    private boolean existsByCNPJ(String CNPJ, long id) {
        return !entityManager.createQuery("SELECT p FROM Parceiro p WHERE CNPJ = :CNPJ and id <> :id")
                .setParameter("CNPJ", CNPJ)
                .setParameter("id", id)
                .getResultList()
                .isEmpty();
    }

    public Parceiro save(Parceiro parceiro) {

        Endereco endereco = parceiro.getEndereco();        
        entityManager.persist(endereco);

        parceiro.setEndereco(endereco);

        valida(parceiro);

        entityManager.persist(parceiro);

        return parceiro;
    }

    public Parceiro update(long id, Parceiro parceiro) {

        parceiro.setId(id);

        valida(parceiro);

        Endereco endereco = parceiro.getEndereco();
        entityManager.merge(endereco);

        parceiro.setEndereco(endereco);

        entityManager.merge(parceiro);

        return parceiro;
    }

    public void delete(long id) {
        Parceiro parceiro = findById(id);

        if (parceiro == null) {
            throw new WebApplicationException("Parceiro não encontrado com ID " + id);
        }

        entityManager.remove(parceiro);
    }

    private void valida(Parceiro parceiro) {

        if (parceiro.getId() > 0) {
            if (existsByNome(parceiro.getRazaoSocial(), parceiro.getId())) {
                throw new WebApplicationException("O parceiro " + parceiro.getRazaoSocial() + " já possui cadastro");
            }

            if (existsByCNPJ(parceiro.getCNPJ(), parceiro.getId())) {
                throw new WebApplicationException("O parceiro com CNPJ " + parceiro.getCNPJ() + " já possui cadastro");
            }

        } else {
            if (existsByNome(parceiro.getRazaoSocial())) {
                throw new WebApplicationException("O parceiro " + parceiro.getRazaoSocial() + " já possui cadastro");
            }

            if (existsByCNPJ(parceiro.getCNPJ())) {
                throw new WebApplicationException("O parceiro com CNPJ " + parceiro.getCNPJ() + " já possui cadastro");
            }
        }

    }
}

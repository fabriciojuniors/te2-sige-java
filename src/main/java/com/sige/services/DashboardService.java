/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.services;

import com.sige.models.DashboardLocalXEvento;
import com.sige.models.DashboardParceiroXEvento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fabri
 */
@Stateless
public class DashboardService {

    @PersistenceContext(unitName = "SigePU")
    private EntityManager entityManager;

    public List<DashboardLocalXEvento> findByLocal() {

        List<DashboardLocalXEvento> result = entityManager.createQuery("select new com.sige.models.DashboardLocalXEvento(count(e.id), e.local) from Evento e group by e.local").getResultList();

        return result;
    }

    public List<DashboardParceiroXEvento> findByParceiro() {

        List<DashboardParceiroXEvento> result = entityManager.createQuery("select new com.sige.models.DashboardParceiroXEvento(count(e.id), e.parceiro) from Evento e group by e.parceiro").getResultList();

        return result;
    }

}

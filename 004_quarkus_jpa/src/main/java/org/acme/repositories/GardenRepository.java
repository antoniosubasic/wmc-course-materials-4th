package org.acme.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.entities.Garden;

import java.util.List;

@ApplicationScoped
public class GardenRepository {
    @Inject
    EntityManager em;

    @Transactional
    public List<Garden> findAll() {
        return em.createQuery("select g from Garden g", Garden.class).getResultList();
    }

    @Transactional
    public Garden findById(Long id) {
        return em.find(Garden.class, id);
    }

    @Transactional
    public Garden save(Garden garden) {
        return em.merge(garden);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("delete from Garden where id=:id", Garden.class).setParameter("id", id).executeUpdate();
    }
}

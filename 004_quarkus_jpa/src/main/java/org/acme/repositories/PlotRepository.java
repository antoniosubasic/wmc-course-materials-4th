package org.acme.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.entities.Plot;

import java.util.List;

@ApplicationScoped
public class PlotRepository {

    @Inject
    EntityManager em;

    @Transactional
    public List<Plot> findAll() {
        return em.createQuery("select p from Plot p", Plot.class).getResultList();
    }

    @Transactional
    public Plot findById(Long id) {
        return em.find(Plot.class, id);
    }

    @Transactional
    public Plot save(Plot plot) {
        return em.merge(plot);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("delete from Plot where id=:id", Plot.class).setParameter("id", id).executeUpdate();
    }
}

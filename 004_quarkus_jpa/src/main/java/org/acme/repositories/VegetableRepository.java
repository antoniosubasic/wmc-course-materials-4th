package org.acme.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.entities.Plot;
import org.acme.entities.Vegetable;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class VegetableRepository {

    @Inject
    EntityManager em;

    @Transactional
    public List<Vegetable> findAll() {
        return em.createQuery("select v from Vegetable v", Vegetable.class).getResultList();
    }

    @Transactional
    public Vegetable findById(Long id) {
        return em.find(Vegetable.class, id);
    }

    @Transactional
    public Vegetable save(Vegetable vegetable) {
        return em.merge(vegetable);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createQuery("delete from Vegetable where id=:id", Vegetable.class).setParameter("id", id).executeUpdate();
    }

    // Example for an arbitrary helper method.
    @Transactional
    public void unlinkVegetableFromPlots(Vegetable vegetable) {
        Set<Plot> plots = vegetable.getPlots();
        for(Plot plot : plots) {
            plot.removeVegetable(vegetable);
            em.persist(plot);
        }
        vegetable.setPlots(Set.of());
        em.persist(vegetable);
    }
}

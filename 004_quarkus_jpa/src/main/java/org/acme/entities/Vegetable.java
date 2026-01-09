package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Collection;
import java.util.Set;

@Entity
public class Vegetable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer nutritionalValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(Integer nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    @JsonIgnore // IMPORTANT. Should not be loaded for REST... LazyInitializationException!
    @ManyToMany(mappedBy = "vegetables")
    private Set<Plot> plots;

    public Set<Plot> getPlots() {
        return plots;
    }

    public void setPlots(Set<Plot> plots) {
        this.plots = plots;
    }

    // Convenience-method 1.
    public void addPlot(Plot plot) {
        plot.addVegetable(this);
    }

    // Convenience-method 2.
    public void removePlot(Plot plot) {
        plot.removeVegetable(this);
    }
}

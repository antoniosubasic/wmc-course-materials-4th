package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Plot {
    @Id
    @GeneratedValue
    private Long id;

    private Double width;
    private Double height;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    // Owning-side... Holds garden_id.
    @JsonIgnore // IMPORTANT. Should not be loaded for REST... LazyInitializationException!
    @ManyToOne
    @JoinColumn(name = "garden_id")
    private Garden garden;

    public Garden getGarden() {
        return garden;
    }

    // Owning-side... set other side as well.
    public void setGarden(Garden garden) {
        if (this.garden != null) {
            this.garden.getPlots().remove(this);
        }
        this.garden = garden;
        if (garden != null) {
            garden.getPlots().add(this);
        }
    }

    // Owning-side... Holds JoinTable settings.
    @JsonIgnore // IMPORTANT. Should not be loaded for REST... LazyInitializationException!
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "plot_id"), inverseJoinColumns = @JoinColumn(name = "vegetable_id"))
    private Set<Vegetable> vegetables;

    public Set<Vegetable> getVegetables() {
        return vegetables;
    }

    public void setVegetables(Set<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    // Owning-side... set other side as well.
    public void addVegetable(Vegetable vegetable) {
        vegetable.getPlots().add(this);
        vegetables.add(vegetable);
    }

    // Owning-side... set other side as well.
    public void removeVegetable(Vegetable vegetable) {
        vegetable.getPlots().remove(this);
        vegetables.remove(vegetable);
    }
}

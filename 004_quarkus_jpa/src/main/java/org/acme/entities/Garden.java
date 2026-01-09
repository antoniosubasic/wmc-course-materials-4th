package org.acme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Collection;

@Entity
public class Garden {
    @Id
    @GeneratedValue
    private Long id;

    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonIgnore // IMPORTANT. Should not be loaded for REST... LazyInitializationException!
    @OneToMany(mappedBy = "garden")
    private Collection<Plot> plots;

    public Collection<Plot> getPlots() {
        return plots;
    }

    public void setPlots(Collection<Plot> plots) {
        this.plots = plots;
    }

    // Convenience-method 1.
    public void addPlot(Plot plot) {
        plot.setGarden(this);
    }

    // Convenience-method 2.
    public void removePlot(Plot plot) {
        plot.setGarden(null);
    }
}

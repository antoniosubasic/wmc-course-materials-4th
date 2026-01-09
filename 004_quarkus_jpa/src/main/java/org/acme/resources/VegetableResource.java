package org.acme.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entities.Vegetable;
import org.acme.repositories.VegetableRepository;

@Path("/vegetables")
public class VegetableResource {

    @Inject
    VegetableRepository vegetableRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(vegetableRepository.findAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        return Response.ok().entity(vegetableRepository.findById(id)).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Vegetable vegetable) {
        vegetable.setId(id);
        return Response.ok().entity(vegetableRepository.save(vegetable)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Vegetable vegetable) {
        return Response.ok().entity(vegetableRepository.save(vegetable)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        vegetableRepository.deleteById(id);
        return Response.ok().build();
    }
}

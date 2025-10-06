package at.ac.htl.leonding.wmc4.demo;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.Map;

@Path("/students")
public class StudentResource {

    DatabaseService ds;

    public StudentResource(DatabaseService ds) {
        ds.reset();
        this.ds = ds;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response students() {
        return Response.ok(ds.getStudents()).build();
    }

    @GET
    @Path("/{id}")
    public Response getStudent(@PathParam("id") long id) {
        Student s = ds.getStudent(id);
        if (s == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Student not found")).build();
        }
        return Response.ok(s).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(Student incoming, @Context UriInfo uriInfo) {
        incoming.setId(ds.inc());
        ds.students().put(incoming.getId(), incoming);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(incoming.getId() + "").build())
                .entity(incoming).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") long id) {
        ds.students().remove(id);
        return Response.noContent().build(); // 204 - no content
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") long id, Student incoming) {
        if (!ds.students().containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Student not found")).build();
        }

        incoming.setId(id);
        ds.students().put(id, incoming);
        return Response.ok(incoming).build(); // 200 + updated object
    }
}

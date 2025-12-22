package at.htl.resources;

import java.util.Map;
import java.util.UUID;
import org.jboss.resteasy.reactive.RestStreamElementType;
import at.htl.entities.Message;
import at.htl.services.EventBusService;
import at.htl.services.MessageService;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/connections")
public class ConnectionResource {
    @Inject
    EventBusService bus;

    @Inject
    MessageService messageService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String register() {
        return UUID.randomUUID().toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void sendMessage(Message message) {
        messageService.create(message);
        bus.publish(Map.of("userId", message.getUserId(), "message", message.getText(), "timestamp",
                message.getTimestamp()));
    }

    @GET
    @Path("/events")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<Object> events() {
        return bus.eventStream();
    }
}

package at.htl.resources;

import at.htl.entities.Message;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface MessagesResource extends PanacheEntityResource<Message, Long> {
}

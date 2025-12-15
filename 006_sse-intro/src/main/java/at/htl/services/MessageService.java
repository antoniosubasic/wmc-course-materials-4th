package at.htl.services;

import at.htl.entities.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MessageService {
    @Transactional
    public void create(Message message) {
        Message m = new Message();
        m.setUserId(message.getUserId());
        m.setText(message.getText());
        m.setTimestamp(message.getTimestamp());
        m.persistAndFlush();
    }
}

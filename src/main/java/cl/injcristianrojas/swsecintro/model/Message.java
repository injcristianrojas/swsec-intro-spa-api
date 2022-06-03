package cl.injcristianrojas.swsecintro.model;

public class Message {

    private final Long id;
    private final String message;

    public Message(long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}

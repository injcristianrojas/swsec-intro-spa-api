package cl.injcristianrojas.swsecintro.model;

public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final int user_type;

    public User(long id, String username, String password, int user_type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.user_type = user_type;
    }

    public User(long id, String username, int user_type) {
        this.id = id;
        this.username = username;
        this.password = "HIDDEN";
        this.user_type = user_type;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getUser_type() {
        return user_type;
    }
}

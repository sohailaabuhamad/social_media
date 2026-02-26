package user;

public class User {
    private int userId;
    private String name;
    private String email;
    private String passwordHash;

    // Constructor for registering a NEW user (no ID yet, database auto-generates it)
    public User(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // Constructor for fetching an EXISTING user from the database
    public User(int userId, String name, String email, String passwordHash) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
}
package hw1.common;

public class User {
    private String login;
    private String password;
    private String ip;
    private String port;

    public User(String login, String password, String ip, String port) {
        this.login = login;
        this.password = password;
        this.ip = ip;
        this.port = port;
    }
    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.ip = "1.1.1.1";
        this.port = "1234";
    }
    public User(String[] args) {
        this.login = args[2];
        this.password = args[3];
        this.ip = args[0];
        this.port = args[1];
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        User user2 = null;
        if (obj instanceof User)
            user2 = (User) obj;
        return user2 != null && login.equalsIgnoreCase(user2.getLogin()) && password.equals(user2.getPassword()) &&
                ip.equalsIgnoreCase(user2.ip) && port.equalsIgnoreCase(user2.port);
    }
}

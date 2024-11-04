package hw1.client;

public class User {
    private String login;
    private String password;
    private User user;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public User(User user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Проверяю пользователя.");
        User user2 = null;
        if (obj instanceof User)
            user2 = (User) obj;
        return user2 != null && login.equalsIgnoreCase(user2.getLogin()) && password.equals(user2.getPassword());
    }
}

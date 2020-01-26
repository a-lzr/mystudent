package entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String login;
    private String pswd;

    public User(String login, String pswd) {
        this.login = login;
        this.pswd = pswd;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public boolean equals(String pswd) {
        return this.pswd.equals(pswd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) &&
                pswd.equals(user.pswd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, pswd);
    }
}
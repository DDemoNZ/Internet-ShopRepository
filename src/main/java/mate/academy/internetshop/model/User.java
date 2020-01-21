package mate.academy.internetshop.model;

import java.util.HashSet;
import java.util.Set;

public class User {

    private String userName;
    private String password;
    private String firstName;
    private String secondName;
    private Long userId;
    private String token;
    private Set<Role> roles = new HashSet<>();

    public User(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{"
                + "userName='" + userName + '\''
                + ", password='" + password + '\''
                + ", firstName='" + firstName + '\''
                + ", secondName='" + secondName + '\''
                + ", userId=" + userId
                + ", token='" + token + '\''
                + ", roles=" + roles
                + '}';
    }
}

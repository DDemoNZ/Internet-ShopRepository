package mate.academy.internetshop.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

    private String userName;
    private String password;
    private byte[] salt;
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

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userName, user.userName)
                && Objects.equals(password, user.password)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(secondName, user.secondName)
                && Objects.equals(userId, user.userId)
                && Objects.equals(token, user.token)
                && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, firstName, secondName, userId, token, roles);
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

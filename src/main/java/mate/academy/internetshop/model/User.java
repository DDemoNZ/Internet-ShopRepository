package mate.academy.internetshop.model;

public class User {

    private String userName;
    private String password;
    private String firstName;
    private String secondName;
    private Long userId;
//    private Bucket bucket;

    public User(String userName) {
        this.userName = userName;
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

//    public Bucket getBucket() {
//        return bucket;
//    }
//
//    public void setBucket(Bucket bucket) {
//        this.bucket = bucket;
//    }

    @Override
    public String toString() {
        return "User{"
                + "userName='" + userName + '\''
                + ", password='" + password + '\''
                + ", firstName='" + firstName + '\''
                + ", secondName='" + secondName + '\''
                + ", userId=" + userId
//                + ", bucket=" + bucket
                + '}';
    }
}

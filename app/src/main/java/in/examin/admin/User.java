package in.examin.admin;

/**
 * Created by examin on 7/9/2017.
 */
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {return address;}

    public void setAddress(String name) {
        this.address = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

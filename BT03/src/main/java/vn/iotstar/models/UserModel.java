package vn.iotstar.models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;  // Thêm id để FK category
    private String username;
    private String fullname;
    private String email;
    private String sdt;
    private String password;

    public UserModel() {
        super();
    }

    public UserModel(int id, String username, String fullname, String email, String sdt, String password) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.sdt = sdt;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel [id=" + id + ", username=" + username + ", fullname=" + fullname + ", email=" + email + ", sdt=" + sdt + ", password=" + password + "]";
    }
}
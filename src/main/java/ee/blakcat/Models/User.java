package ee.blakcat.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.UUID;
@Table (name = "user_data")
@Entity
public class User {
    @Id
    private String id;
    private String login, password, nameSurname, address;



    public User(String id, String login, String password, String nameSurname, String address) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nameSurname = nameSurname;
        this.address = address;
    }

    public User(String login, String password, String nameSurname, String address){
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.nameSurname = nameSurname;
        this.address = address;

    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nameSurname='" + nameSurname + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

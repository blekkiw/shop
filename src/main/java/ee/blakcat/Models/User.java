package ee.blakcat.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Table (name = "user_data")
@Entity
public class User {
    @Id
    private String id;
    @Column (unique = true)
    private String login;
       private String password, nameSurname, address;
    private Status status;
    private UserRole userRole;
    private String session;
    @OneToMany (cascade = CascadeType.ALL, targetEntity = Cart.class)
private List <Cart> carts;


    public void setSession(String session) {
        this.session = session;
    }

    public User(String id, String login, String password, String nameSurname, String address) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nameSurname = nameSurname;
        this.address = address;
        this.userRole= UserRole.CUSTOMER;
        this.status= Status.ACTIVE;
        this.carts =new ArrayList<>();
    }

    public User(String login, String password, String nameSurname, String address){
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.nameSurname = nameSurname;
        this.address = address;
        this.userRole=UserRole.CUSTOMER;
this.status=Status.ACTIVE;
        this.carts =new ArrayList<>();
    }

    public User() {
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> cart) {
        this.carts = cart;
    }

    public Cart getActiveCart () {
        if (carts.isEmpty()) carts.add(new Cart(this));
      return carts.stream().findFirst().filter(cart -> cart.getProcessStatus()==ProcessStatus.ACTIVE).get();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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
    public String getSession() {
        return session;
    }



    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", nameSurname='" + nameSurname + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

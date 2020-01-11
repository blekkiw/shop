package ee.blakcat.Models;

import java.util.ArrayList;

public class User {
    private String login, password, nameSurname, id, address;
    private ArrayList <Product> products;

    public User(String login, String password, String nameSurname, String id, String address) {
        this.login = login;
        this.password = password;
        this.nameSurname = nameSurname;
        this.id = id;
        this.address = address;
        this.products = new ArrayList<>();
    }

    public User(String login, String password, String nameSurname, String address){
        this.login = login;
        this.password = password;
        this.nameSurname = nameSurname;
        this.address = address;
        this.products=new ArrayList<>();
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", nameSurname='" + nameSurname + '\'' +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", products=" + products +
                '}';
    }
}

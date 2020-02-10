package ee.blakcat.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table (name = "Carts")
@Entity
public class Cart implements Serializable {
    @Id
   private String id;
    @OneToOne (cascade = CascadeType.ALL)
   private User customer;
    @OneToOne (cascade = CascadeType.ALL)
   private Payment payment;
    @ManyToMany (cascade = CascadeType.ALL, targetEntity = Product.class)
   private List<Product> products;
    private ProcessStatus processStatus;

    public Cart() {
    }

    public Cart(String id, User user, Payment payment, ArrayList<Product> products) {
        this.id = id;
        this.customer = user;
        this.payment = payment;
        this.products = products;
        this.processStatus=ProcessStatus.ACTIVE;
    }


    public Cart(User user) {
        this.id = UUID.randomUUID().toString();
        this.customer = user;
        this.payment= new Payment(user);
        this.products = new ArrayList<>();
        this.processStatus=ProcessStatus.ACTIVE;
    }

    public String getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public boolean isEmpty () {
        if (this.products == null|| this.products.isEmpty()) return true;
        return false;
    }

    public double getTotalPrice () {
        double totalPrice=0;
        for (Product product : products) {
            totalPrice+=product.getPrice();
        }
        return totalPrice;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    public void add (Product product) {
        this.products.add(product);
    }

    public void delete (Product product) {
        this.products.remove(product);
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return customer;
    }

    public void setUser(User user) {
        this.customer = user;
    }

    public Payment getPaymentId() {
        return payment;
    }

    public void setPaymentId(Payment payment) {
        this.payment= payment;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", payment=" + payment +
                ", products=" + products +
                ", processStatus=" + processStatus +
                '}';
    }
}

package ee.blakcat.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table (name = "payments")
public class Payment{
    @Id
   private String id;
    @OneToOne(cascade = CascadeType.ALL)
   private User customer;
   private PaymentStatus paymentStatus;

    public Payment(String id, User user, PaymentStatus paymentStatus) {
        this.id = id;
        this.customer = user;
        this.paymentStatus = paymentStatus;
    }

    public Payment(User user) {
        this.id= UUID.randomUUID().toString();
        this.customer = user;
        this.paymentStatus= PaymentStatus.PROCESS;
    }

    public Payment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUserId() {
        return customer;
    }

    public void setUserId(User user) {
        this.customer = user;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

package ee.blakcat.Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name = "payments")
public class Payment{
    @Id
   private String id;
    @OneToOne(cascade = CascadeType.ALL)
   private User customer;
   private ProcessStatus processStatus;

    public Payment(String id, User user, ProcessStatus processStatus) {
        this.id = id;
        this.customer = user;
        this.processStatus = processStatus;
    }

    public Payment(User user) {
        this.id= UUID.randomUUID().toString();
        this.customer = user;
        this.processStatus = ProcessStatus.TOPAY;
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

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }
}

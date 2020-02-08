package ee.blakcat.Repositories;

import ee.blakcat.Models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository <Payment, String> {
}

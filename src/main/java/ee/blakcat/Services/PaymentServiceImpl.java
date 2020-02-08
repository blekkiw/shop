package ee.blakcat.Services;

import ee.blakcat.Models.Payment;
import ee.blakcat.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl extends BaseAbstractService <PaymentRepository, Payment, String> implements PaymentService {
    PaymentRepository paymentRepository;
@Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }
}

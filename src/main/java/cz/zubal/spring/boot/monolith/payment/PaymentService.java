package cz.zubal.spring.boot.monolith.payment;

import cz.zubal.spring.boot.monolith.order.RepositoryOrderService;
import cz.zubal.spring.boot.monolith.order.api.Order;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final RepositoryOrderService orderService;

    public PaymentService(RepositoryOrderService orderService) {
        this.orderService = orderService;
    }

    public PaymentEntity processPayment(String orderId, String method) {
        Order order = orderService.findByUuid(orderId);
        if (order == null) {
            throw new RuntimeException("Order not found: " + orderId);
        }

        PaymentEntity payment = new PaymentEntity();
        payment.setId(System.currentTimeMillis());
        payment.setOrderId(orderId);
        payment.setAmount(order.getPrice());
        payment.setMethod(method);
        payment.setStatus("SUCCESS"); // simplified logic

        orderService.markOrderPaid(orderId);
        return payment;
    }
}
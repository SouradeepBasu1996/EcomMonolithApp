package cz.zubal.spring.boot.monolith.payment;

import lombok.Data;

@Data
public class PaymentEntity {
    public Long id;
    public String orderId;
    public Double amount;
    public String status;
    public String method;
}
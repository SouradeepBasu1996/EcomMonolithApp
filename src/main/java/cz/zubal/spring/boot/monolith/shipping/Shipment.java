package cz.zubal.spring.boot.monolith.shipping;

import lombok.Data;

@Data
public class Shipment {
    private Long id;
    private String orderId;
    private String trackingId;
    private String status; // CREATED, IN_TRANSIT, DELIVERED
}
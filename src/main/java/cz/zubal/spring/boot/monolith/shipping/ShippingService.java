package cz.zubal.spring.boot.monolith.shipping;
import cz.zubal.spring.boot.monolith.order.RepositoryOrderService;
import cz.zubal.spring.boot.monolith.order.api.Order;
import cz.zubal.spring.boot.monolith.payment.PaymentService;

public class ShippingService {

    private final RepositoryOrderService orderService;
    private final PaymentService paymentService;

    public ShippingService(RepositoryOrderService orderService,
                           PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    public Shipment createShipment(String orderId) {
        // Ensure order exists
        Order order = orderService.findByUuid(orderId);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        // Ensure customer has paid
        // (Simplified: assume payment success already handled)

        Shipment shipment = new Shipment();
        shipment.setId(System.currentTimeMillis());
        shipment.setOrderId(orderId);
        shipment.setTrackingId("TRK-" + System.currentTimeMillis());
        shipment.setStatus("CREATED");

        orderService.markOrderShipped(orderId);
        return shipment;
    }
}
package template.order.publisher;

import template.order.domain.dto.OrderDto;

public interface OrderEventPublisher {

    void publishOrderCreated(OrderDto.Publish publishOrder);
    void publishOrderAccepted(OrderDto.Accept acceptOrder);
    void publishOrderRejected(OrderDto.Reject rejectOrder);
    void publishOrderCancelled(OrderDto.Cancel rejectedOrder);
    void publishOrderWithdrew(OrderDto.Withdraw rejectedOrder);
    void publishOrderStarted(OrderDto.Start startOrder);
    void publishOrderPrepared(OrderDto.Prepare prepareForPickUpOrder);
    void publishOrderSent(OrderDto.Send rejectedOrder);
    void publishOrderDelivered(OrderDto.Deliver deliverOrder);
}

package template.order.publisher;

import template.order.domain.dto.OrderDto;

public interface OrderEventPublisher {
    void publishOrderCreated(OrderDto.Publish publishOrder);
    void publishOrderAccepted(OrderDto.Accept acceptOrder);
    void publishOrderRejected(OrderDto.Reject rejectOrder);
    void publishOrderCancelled(OrderDto.Cancel rejectedOrder);
    void publishOrderWithdraw(OrderDto.Withdraw rejectedOrder);
    void publishOrderPreparing(OrderDto.Prepare prepareOrder);
    void publishOrderReadyForPickUp(OrderDto.Ready readyForPickUpOrder);
}

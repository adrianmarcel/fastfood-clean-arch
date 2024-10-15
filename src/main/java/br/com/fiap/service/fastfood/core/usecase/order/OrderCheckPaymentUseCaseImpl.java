package br.com.fiap.service.fastfood.core.usecase.order;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderStatus;
import br.com.fiap.service.fastfood.gateway.domain.order.OrderSearchGateway;
import br.com.fiap.service.fastfood.gateway.domain.order.OrderUpdateGateway;
import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCheckPaymentUseCaseImpl implements OrderCheckPaymentUseCase {

  private final OrderSearchGateway searchGateway;
  private final OrderUpdateGateway updateGateway;

  @Override
  public void execute(OrderStatus status) {
    var orders =
        searchGateway
            .execute(OrderFilterRequest.builder().status(status).build(), Pageable.unpaged())
            .getContent();

    orders.forEach(
        order -> {
          var gatewayRequest = OrderGatewayRequest.mapper(order);
          gatewayRequest.setStatus(OrderStatus.PAGO.getDescription());

          updateGateway.execute(gatewayRequest);
        });
  }
}

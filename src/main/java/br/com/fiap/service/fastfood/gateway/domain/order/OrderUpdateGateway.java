package br.com.fiap.service.fastfood.gateway.domain.order;

import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayResponse;
import java.util.Optional;

public interface OrderUpdateGateway {

  Optional<OrderGatewayResponse> execute(OrderGatewayRequest request);
}

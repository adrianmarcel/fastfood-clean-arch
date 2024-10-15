package br.com.fiap.service.fastfood.gateway.domain.order;

import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayResponse;

public interface OrderCreateGateway {

  OrderGatewayResponse execute(OrderGatewayRequest request);
}

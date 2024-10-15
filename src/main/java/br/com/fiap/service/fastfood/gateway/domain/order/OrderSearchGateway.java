package br.com.fiap.service.fastfood.gateway.domain.order;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderFilterRequest;
import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderSearchGateway {

  Page<OrderGatewayResponse> execute(OrderFilterRequest filterRequest, Pageable pageable);
}

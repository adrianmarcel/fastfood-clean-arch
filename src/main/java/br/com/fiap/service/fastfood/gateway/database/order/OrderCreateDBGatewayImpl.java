package br.com.fiap.service.fastfood.gateway.database.order;

import br.com.fiap.service.fastfood.gateway.database.order.model.OrderEntity;
import br.com.fiap.service.fastfood.gateway.database.order.repository.OrderRepository;
import br.com.fiap.service.fastfood.gateway.domain.order.OrderCreateGateway;
import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCreateDBGatewayImpl implements OrderCreateGateway {

  private final OrderRepository repository;

  @Override
  public OrderGatewayResponse execute(OrderGatewayRequest request) {
    return OrderGatewayResponse.mapper(repository.save(OrderEntity.mapper(request)));
  }
}

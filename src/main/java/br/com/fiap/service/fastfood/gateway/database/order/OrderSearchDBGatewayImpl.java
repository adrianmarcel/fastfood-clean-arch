package br.com.fiap.service.fastfood.gateway.database.order;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderFilterRequest;
import br.com.fiap.service.fastfood.gateway.database.order.repository.OrderRepository;
import br.com.fiap.service.fastfood.gateway.database.order.specification.OrderSpecification;
import br.com.fiap.service.fastfood.gateway.domain.order.OrderSearchGateway;
import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSearchDBGatewayImpl implements OrderSearchGateway {

  private final OrderRepository repository;

  @Override
  public Page<OrderGatewayResponse> execute(OrderFilterRequest filterRequest, Pageable pageable) {
    return repository
        .findAll(OrderSpecification.build(filterRequest), pageable)
        .map(OrderGatewayResponse::mapper);
  }
}

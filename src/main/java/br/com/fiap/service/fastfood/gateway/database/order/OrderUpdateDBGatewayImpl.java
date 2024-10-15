package br.com.fiap.service.fastfood.gateway.database.order;

import br.com.fiap.service.fastfood.gateway.database.order.repository.OrderRepository;
import br.com.fiap.service.fastfood.gateway.domain.order.OrderUpdateGateway;
import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayResponse;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderUpdateDBGatewayImpl implements OrderUpdateGateway {

  private final OrderRepository repository;

  @Override
  @Transactional
  public Optional<OrderGatewayResponse> execute(OrderGatewayRequest request) {
    return repository
        .findById(request.getId())
        .map(
            order -> {
              order.setStatus(request.getStatus());
              return OrderGatewayResponse.mapper(repository.save(order));
            });
  }
}

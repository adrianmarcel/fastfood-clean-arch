package br.com.fiap.service.fastfood.core.usecase.order;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderResponse;
import br.com.fiap.service.fastfood.gateway.domain.order.OrderSearchGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSearchUseCaseImpl implements OrderSearchUseCase {

  private final OrderSearchGateway searchGateway;

  @Override
  public Page<OrderResponse> execute(OrderFilterRequest filterRequest, Pageable pageable) {
    return searchGateway.execute(filterRequest, pageable).map(OrderResponse::mapper);
  }
}

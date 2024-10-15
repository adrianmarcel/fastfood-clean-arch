package br.com.fiap.service.fastfood.core.usecase.order;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderStatus;
import br.com.fiap.service.fastfood.gateway.domain.order.OrderSearchGateway;
import br.com.fiap.service.fastfood.gateway.domain.order.OrderUpdateGateway;
import br.com.fiap.service.fastfood.gateway.domain.order.model.OrderGatewayRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderFinishUseCaseImpl implements OrderFinishUseCase {

  private final OrderUpdateGateway updateGateway;
  private final OrderSearchGateway searchGateway;

  @Override
  public void execute(UUID id) {
    searchGateway
        .execute(OrderFilterRequest.builder().id(id).build(), Pageable.unpaged())
        .stream()
        .findFirst()
        .ifPresentOrElse(
            order -> {
              if (!OrderStatus.RECEBIDO.getDescription().equalsIgnoreCase(order.getStatus())) {
                throw new BusinessException(ExceptionCode.ORDER_NOT_PAID);
              }
              var gatewayRequest = OrderGatewayRequest.mapper(order);
              gatewayRequest.setStatus(OrderStatus.PRONTO.getDescription());

              updateGateway.execute(gatewayRequest);
            },
            () -> {
              throw new BusinessException(ExceptionCode.ORDER_NOT_FOUND);
            });
  }
}

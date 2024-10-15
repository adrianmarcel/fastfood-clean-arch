package br.com.fiap.service.fastfood.core.usecase.order;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderStatus;

public interface OrderCheckPaymentUseCase {

  void execute(OrderStatus status);
}

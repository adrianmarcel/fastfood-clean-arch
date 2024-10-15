package br.com.fiap.service.fastfood.core.usecase.order;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderRequest;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderResponse;
import jakarta.validation.Valid;

public interface OrderCreateUseCase {

  OrderResponse execute(@Valid OrderRequest request);
}

package br.com.fiap.service.fastfood.core.usecase.order;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderSearchUseCase {

  Page<OrderResponse> execute(@Valid OrderFilterRequest request, Pageable pageable);
}

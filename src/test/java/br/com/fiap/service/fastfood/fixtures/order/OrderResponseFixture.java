package br.com.fiap.service.fastfood.fixtures.order;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderResponse;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderStatus;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public class OrderResponseFixture {

  public static OrderResponse validResponse() {
    return OrderResponse.builder()
        .id(UUID.fromString("a6cfa873-1012-4596-a379-04ad2edab536"))
        .customer(UUID.fromString("919d8d5b-d72d-45a8-923b-86ed31a5e280"))
        .products(List.of(OrderProductRequestFixture.validRequest()))
        .status(OrderStatus.RECEBIDO.getDescription())
        .mercadoPagoPaymentId(UUID.fromString("759b8987-9dcf-47f5-96e4-4be77018c4af"))
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .build();
  }
}

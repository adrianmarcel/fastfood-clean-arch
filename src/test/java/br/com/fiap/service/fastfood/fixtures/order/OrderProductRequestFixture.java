package br.com.fiap.service.fastfood.fixtures.order;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderProductRequest;
import java.util.UUID;

public class OrderProductRequestFixture {

  public static OrderProductRequest validRequest() {
    return OrderProductRequest.builder()
        .id(UUID.fromString("a6cfa873-1012-4596-a379-04ad2edab536"))
        .quantity(1)
        .build();
  }
}

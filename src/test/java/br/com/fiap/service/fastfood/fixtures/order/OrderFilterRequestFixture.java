package br.com.fiap.service.fastfood.fixtures.order;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderFilterRequest;
import java.util.UUID;

public class OrderFilterRequestFixture {

  public static OrderFilterRequest validRequest() {
    return OrderFilterRequest.builder()
        .id(UUID.fromString("a6cfa873-1012-4596-a379-04ad2edab536"))
        .build();
  }
}

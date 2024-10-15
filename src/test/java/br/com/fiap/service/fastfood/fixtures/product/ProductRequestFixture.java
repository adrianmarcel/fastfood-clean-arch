package br.com.fiap.service.fastfood.fixtures.product;

import br.com.fiap.service.fastfood.core.usecase.product.model.ProductRequest;
import java.math.BigDecimal;
import java.util.UUID;

public class ProductRequestFixture {

  public static ProductRequest validRequest() {
    return ProductRequest.builder()
        .name("Product test")
        .category(UUID.fromString("919d8d5b-d72d-45a8-923b-86ed31a5e280"))
        .amount(new BigDecimal("20.00"))
        .build();
  }
}

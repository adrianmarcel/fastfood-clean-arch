package br.com.fiap.service.fastfood.fixtures.product;

import br.com.fiap.service.fastfood.core.usecase.product.model.ProductResponse;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class ProductResponseFixture {

  public static ProductResponse validResponse() {
    return ProductResponse.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .name("Product test")
        .category(UUID.fromString("919d8d5b-d72d-45a8-923b-86ed31a5e280"))
        .amount(new BigDecimal("20.00"))
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .build();
  }

  public static ProductResponse updatedResponse() {
    return ProductResponse.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .name("Product test")
        .category(UUID.fromString("919d8d5b-d72d-45a8-923b-86ed31a5e280"))
        .amount(new BigDecimal("200.00"))
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .build();
  }
}

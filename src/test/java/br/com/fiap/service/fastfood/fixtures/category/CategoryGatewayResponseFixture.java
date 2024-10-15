package br.com.fiap.service.fastfood.fixtures.category;

import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayResponse;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CategoryGatewayResponseFixture {

  public static CategoryGatewayResponse validRequest() {
    return CategoryGatewayResponse.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .name("Category test")
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .build();
  }
}

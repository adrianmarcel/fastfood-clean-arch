package br.com.fiap.service.fastfood.fixtures.category;

import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryResponse;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CategoryResponseFixture {

  public static CategoryResponse validResponse() {
    return CategoryResponse.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .name("Category test")
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .build();
  }

  public static CategoryResponse updatedResponse() {
    return CategoryResponse.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .name("Category test 2")
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .build();
  }
}

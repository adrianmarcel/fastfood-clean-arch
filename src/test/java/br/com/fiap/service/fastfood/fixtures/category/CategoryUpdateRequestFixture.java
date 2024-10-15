package br.com.fiap.service.fastfood.fixtures.category;

import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryUpdateRequest;

public class CategoryUpdateRequestFixture {

  public static CategoryUpdateRequest validRequest() {
    return CategoryUpdateRequest.builder().name("Category test 2").build();
  }
}

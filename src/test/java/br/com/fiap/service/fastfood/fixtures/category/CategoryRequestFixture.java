package br.com.fiap.service.fastfood.fixtures.category;

import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryRequest;

public class CategoryRequestFixture {

  public static CategoryRequest validRequest() {
    return CategoryRequest.builder().name("Category test").build();
  }
}

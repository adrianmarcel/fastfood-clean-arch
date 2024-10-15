package br.com.fiap.service.fastfood.fixtures.category;

import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayRequest;

public class CategoryGatewayRequestFixture {

  public static CategoryGatewayRequest validRequest() {
    return CategoryGatewayRequest.builder().name("Category test").build();
  }
}

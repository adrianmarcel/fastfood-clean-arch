package br.com.fiap.service.fastfood.gateway.domain.category;

import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayResponse;

public interface CategoryCreateGateway {

  CategoryGatewayResponse execute(CategoryGatewayRequest request);
}

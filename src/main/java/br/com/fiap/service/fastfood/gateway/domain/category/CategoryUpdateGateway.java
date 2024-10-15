package br.com.fiap.service.fastfood.gateway.domain.category;

import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayResponse;
import java.util.Optional;

public interface CategoryUpdateGateway {

  Optional<CategoryGatewayResponse> execute(CategoryGatewayRequest request);
}

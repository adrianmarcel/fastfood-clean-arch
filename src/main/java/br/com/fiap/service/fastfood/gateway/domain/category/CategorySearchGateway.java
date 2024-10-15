package br.com.fiap.service.fastfood.gateway.domain.category;

import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryFilterRequest;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategorySearchGateway {

  Page<CategoryGatewayResponse> execute(CategoryFilterRequest filterRequest, Pageable pageable);
}

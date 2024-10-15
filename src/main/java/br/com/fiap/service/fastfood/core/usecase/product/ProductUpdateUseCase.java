package br.com.fiap.service.fastfood.core.usecase.product;

import br.com.fiap.service.fastfood.core.usecase.product.model.ProductResponse;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductUpdateRequest;
import jakarta.validation.Valid;

public interface ProductUpdateUseCase {

  ProductResponse execute(@Valid ProductUpdateRequest request);
}

package br.com.fiap.service.fastfood.core.usecase.product;

import br.com.fiap.service.fastfood.core.usecase.product.model.ProductRequest;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductResponse;
import jakarta.validation.Valid;

public interface ProductCreateUseCase {

  ProductResponse execute(@Valid ProductRequest request);
}

package br.com.fiap.service.fastfood.core.usecase.product;

import br.com.fiap.service.fastfood.core.usecase.product.model.ProductFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearchUseCase {

  Page<ProductResponse> execute(@Valid ProductFilterRequest request, Pageable pageable);
}

package br.com.fiap.service.fastfood.gateway.domain.product;

import br.com.fiap.service.fastfood.core.usecase.product.model.ProductFilterRequest;
import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearchGateway {

  Page<ProductGatewayResponse> execute(ProductFilterRequest filterRequest, Pageable pageable);
}

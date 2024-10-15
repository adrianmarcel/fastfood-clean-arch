package br.com.fiap.service.fastfood.gateway.domain.product;

import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayResponse;
import java.util.Optional;

public interface ProductUpdateGateway {

  Optional<ProductGatewayResponse> execute(ProductGatewayRequest request);
}

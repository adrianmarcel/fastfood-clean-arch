package br.com.fiap.service.fastfood.gateway.domain.product;

import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayResponse;

public interface ProductCreateGateway {

  ProductGatewayResponse execute(ProductGatewayRequest request);
}

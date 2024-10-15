package br.com.fiap.service.fastfood.gateway.database.product;

import br.com.fiap.service.fastfood.gateway.database.product.model.ProductEntity;
import br.com.fiap.service.fastfood.gateway.database.product.repository.ProductRepository;
import br.com.fiap.service.fastfood.gateway.domain.product.ProductCreateGateway;
import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreateDBGatewayImpl implements ProductCreateGateway {

  private final ProductRepository repository;

  @Override
  public ProductGatewayResponse execute(ProductGatewayRequest request) {
    return ProductGatewayResponse.mapper(repository.save(ProductEntity.mapper(request)));
  }
}

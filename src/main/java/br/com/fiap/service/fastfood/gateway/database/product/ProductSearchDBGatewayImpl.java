package br.com.fiap.service.fastfood.gateway.database.product;

import br.com.fiap.service.fastfood.core.usecase.product.model.ProductFilterRequest;
import br.com.fiap.service.fastfood.gateway.database.product.repository.ProductRepository;
import br.com.fiap.service.fastfood.gateway.database.product.specification.ProductSpecification;
import br.com.fiap.service.fastfood.gateway.domain.product.ProductSearchGateway;
import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSearchDBGatewayImpl implements ProductSearchGateway {

  private final ProductRepository repository;

  @Override
  public Page<ProductGatewayResponse> execute(
      ProductFilterRequest filterRequest, Pageable pageable) {
    return repository
        .findAll(ProductSpecification.build(filterRequest), pageable)
        .map(ProductGatewayResponse::mapper);
  }
}

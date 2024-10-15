package br.com.fiap.service.fastfood.core.usecase.product;

import br.com.fiap.service.fastfood.core.usecase.product.model.ProductFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductResponse;
import br.com.fiap.service.fastfood.gateway.domain.product.ProductSearchGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSearchUseCaseImpl implements ProductSearchUseCase {

  private final ProductSearchGateway searchGateway;

  @Override
  public Page<ProductResponse> execute(ProductFilterRequest filterRequest, Pageable pageable) {
    return searchGateway.execute(filterRequest, pageable).map(ProductResponse::mapper);
  }
}

package br.com.fiap.service.fastfood.core.usecase.product;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductRequest;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductResponse;
import br.com.fiap.service.fastfood.gateway.domain.category.CategorySearchGateway;
import br.com.fiap.service.fastfood.gateway.domain.product.ProductCreateGateway;
import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreateUseCaseImpl implements ProductCreateUseCase {

  private final ProductCreateGateway createGateway;
  private final CategorySearchGateway categorySearchGateway;

  @Override
  public ProductResponse execute(ProductRequest request) {
    categorySearchGateway
        .execute(
            CategoryFilterRequest.builder().id(request.getCategory()).build(), Pageable.unpaged())
        .stream()
        .findFirst()
        .orElseThrow(() -> new BusinessException(ExceptionCode.CATEGORY_NOT_FOUND));

    return ProductResponse.mapper(createGateway.execute(ProductGatewayRequest.mapper(request)));
  }
}

package br.com.fiap.service.fastfood.core.usecase.product;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductResponse;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductUpdateRequest;
import br.com.fiap.service.fastfood.gateway.domain.product.ProductUpdateGateway;
import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUpdateUseCaseImpl implements ProductUpdateUseCase {

  private final ProductUpdateGateway updateGateway;

  @Override
  public ProductResponse execute(ProductUpdateRequest request) {
    return updateGateway
        .execute(ProductGatewayRequest.mapper(request))
        .map(ProductResponse::mapper)
        .orElseThrow(() -> new BusinessException(ExceptionCode.PRODUCT_NOT_FOUND));
  }
}

package br.com.fiap.service.fastfood.core.usecase.product;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductFilterRequest;
import br.com.fiap.service.fastfood.gateway.domain.product.ProductDeleteGateway;
import br.com.fiap.service.fastfood.gateway.domain.product.ProductSearchGateway;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeleteUseCaseImpl implements ProductDeleteUseCase {

  private final ProductDeleteGateway deleteGateway;
  private final ProductSearchGateway searchGateway;

  @Override
  public void execute(UUID id) {
    searchGateway
        .execute(ProductFilterRequest.builder().id(id).build(), Pageable.unpaged())
        .stream()
        .findFirst()
        .ifPresentOrElse(
            product -> deleteGateway.execute(product.getId()),
            () -> {
              throw new BusinessException(ExceptionCode.PRODUCT_NOT_FOUND);
            });
  }
}

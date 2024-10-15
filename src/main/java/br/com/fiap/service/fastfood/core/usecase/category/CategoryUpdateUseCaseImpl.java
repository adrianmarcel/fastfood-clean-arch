package br.com.fiap.service.fastfood.core.usecase.category;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryResponse;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryUpdateRequest;
import br.com.fiap.service.fastfood.gateway.domain.category.CategoryUpdateGateway;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryUpdateUseCaseImpl implements CategoryUpdateUseCase {

  private final CategoryUpdateGateway updateGateway;

  @Override
  public CategoryResponse execute(CategoryUpdateRequest request) {
    return updateGateway
        .execute(CategoryGatewayRequest.mapper(request))
        .map(CategoryResponse::mapper)
        .orElseThrow(() -> new BusinessException(ExceptionCode.CATEGORY_NOT_FOUND));
  }
}

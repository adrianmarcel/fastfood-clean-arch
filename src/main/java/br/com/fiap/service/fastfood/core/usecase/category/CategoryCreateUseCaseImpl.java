package br.com.fiap.service.fastfood.core.usecase.category;

import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryRequest;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryResponse;
import br.com.fiap.service.fastfood.gateway.domain.category.CategoryCreateGateway;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCreateUseCaseImpl implements CategoryCreateUseCase {

  private final CategoryCreateGateway createGateway;

  @Override
  public CategoryResponse execute(CategoryRequest request) {
    return CategoryResponse.mapper(createGateway.execute(CategoryGatewayRequest.mapper(request)));
  }
}

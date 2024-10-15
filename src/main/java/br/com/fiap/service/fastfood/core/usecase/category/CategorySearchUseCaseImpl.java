package br.com.fiap.service.fastfood.core.usecase.category;

import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryResponse;
import br.com.fiap.service.fastfood.gateway.domain.category.CategorySearchGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategorySearchUseCaseImpl implements CategorySearchUseCase {

  private final CategorySearchGateway searchGateway;

  @Override
  public Page<CategoryResponse> execute(CategoryFilterRequest filterRequest, Pageable pageable) {
    return searchGateway.execute(filterRequest, pageable).map(CategoryResponse::mapper);
  }
}

package br.com.fiap.service.fastfood.gateway.database.category;

import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryFilterRequest;
import br.com.fiap.service.fastfood.gateway.database.category.repository.CategoryRepository;
import br.com.fiap.service.fastfood.gateway.database.category.specification.CategorySpecification;
import br.com.fiap.service.fastfood.gateway.domain.category.CategorySearchGateway;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategorySearchDBGatewayImpl implements CategorySearchGateway {

  private final CategoryRepository repository;

  @Override
  public Page<CategoryGatewayResponse> execute(
      CategoryFilterRequest filterRequest, Pageable pageable) {
    return repository
        .findAll(CategorySpecification.build(filterRequest), pageable)
        .map(CategoryGatewayResponse::mapper);
  }
}

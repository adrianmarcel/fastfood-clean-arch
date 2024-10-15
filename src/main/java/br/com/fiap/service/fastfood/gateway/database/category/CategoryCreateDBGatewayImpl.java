package br.com.fiap.service.fastfood.gateway.database.category;

import br.com.fiap.service.fastfood.gateway.database.category.model.CategoryEntity;
import br.com.fiap.service.fastfood.gateway.database.category.repository.CategoryRepository;
import br.com.fiap.service.fastfood.gateway.domain.category.CategoryCreateGateway;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCreateDBGatewayImpl implements CategoryCreateGateway {

  private final CategoryRepository repository;

  @Override
  public CategoryGatewayResponse execute(CategoryGatewayRequest request) {
    return CategoryGatewayResponse.mapper(repository.save(CategoryEntity.mapper(request)));
  }
}

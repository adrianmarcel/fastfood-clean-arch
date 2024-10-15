package br.com.fiap.service.fastfood.gateway.database.category;

import br.com.fiap.service.fastfood.gateway.database.category.repository.CategoryRepository;
import br.com.fiap.service.fastfood.gateway.domain.category.CategoryUpdateGateway;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayResponse;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryUpdateDBGatewayImpl implements CategoryUpdateGateway {

  private final CategoryRepository repository;

  @Override
  public Optional<CategoryGatewayResponse> execute(CategoryGatewayRequest request) {
    return repository
        .findById(request.getId())
        .map(
            product -> {
              var name = Objects.nonNull(request.getName()) ? request.getName() : product.getName();
              product.setName(name);
              return CategoryGatewayResponse.mapper(repository.save(product));
            });
  }
}

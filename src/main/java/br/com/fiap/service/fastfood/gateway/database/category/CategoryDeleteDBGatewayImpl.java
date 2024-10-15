package br.com.fiap.service.fastfood.gateway.database.category;

import br.com.fiap.service.fastfood.gateway.database.category.repository.CategoryRepository;
import br.com.fiap.service.fastfood.gateway.domain.category.CategoryDeleteGateway;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDeleteDBGatewayImpl implements CategoryDeleteGateway {

  private final CategoryRepository repository;

  @Override
  public void execute(UUID id) {
    repository.deleteById(id);
  }
}

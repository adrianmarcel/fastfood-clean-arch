package br.com.fiap.service.fastfood.gateway.database.product;

import br.com.fiap.service.fastfood.gateway.database.product.repository.ProductRepository;
import br.com.fiap.service.fastfood.gateway.domain.product.ProductDeleteGateway;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDeleteDBGatewayImpl implements ProductDeleteGateway {

  private final ProductRepository repository;

  @Override
  public void execute(UUID id) {
    repository.deleteById(id);
  }
}

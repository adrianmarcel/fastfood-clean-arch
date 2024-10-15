package br.com.fiap.service.fastfood.gateway.database.product;

import br.com.fiap.service.fastfood.gateway.database.product.repository.ProductRepository;
import br.com.fiap.service.fastfood.gateway.domain.product.ProductUpdateGateway;
import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.product.model.ProductGatewayResponse;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUpdateDBGatewayImpl implements ProductUpdateGateway {

  private final ProductRepository repository;

  @Override
  public Optional<ProductGatewayResponse> execute(ProductGatewayRequest request) {
    return repository
        .findById(request.getId())
        .map(
            product -> {
              var name = Objects.nonNull(request.getName()) ? request.getName() : product.getName();
              var category =
                  Objects.nonNull(request.getCategory())
                      ? request.getCategory()
                      : product.getCategory();
              var amount =
                  Objects.nonNull(request.getAmount()) ? request.getAmount() : product.getAmount();

              product.setName(name);
              product.setCategory(category);
              product.setAmount(amount);

              return ProductGatewayResponse.mapper(repository.save(product));
            });
  }
}

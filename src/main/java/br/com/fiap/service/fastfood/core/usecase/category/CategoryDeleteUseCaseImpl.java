package br.com.fiap.service.fastfood.core.usecase.category;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryFilterRequest;
import br.com.fiap.service.fastfood.gateway.domain.category.CategoryDeleteGateway;
import br.com.fiap.service.fastfood.gateway.domain.category.CategorySearchGateway;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDeleteUseCaseImpl implements CategoryDeleteUseCase {

  private final CategoryDeleteGateway deleteGateway;
  private final CategorySearchGateway searchGateway;

  @Override
  public void execute(UUID id) {
    searchGateway
        .execute(CategoryFilterRequest.builder().id(id).build(), Pageable.unpaged())
        .stream()
        .findFirst()
        .ifPresentOrElse(
            category -> deleteGateway.execute(category.getId()),
            () -> {
              throw new BusinessException(ExceptionCode.CATEGORY_NOT_FOUND);
            });
  }
}

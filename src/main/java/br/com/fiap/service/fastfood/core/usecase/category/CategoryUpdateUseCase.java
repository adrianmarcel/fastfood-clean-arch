package br.com.fiap.service.fastfood.core.usecase.category;

import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryResponse;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryUpdateRequest;
import jakarta.validation.Valid;

public interface CategoryUpdateUseCase {

  CategoryResponse execute(@Valid CategoryUpdateRequest request);
}

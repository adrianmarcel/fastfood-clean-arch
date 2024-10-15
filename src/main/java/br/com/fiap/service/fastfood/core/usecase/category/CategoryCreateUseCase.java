package br.com.fiap.service.fastfood.core.usecase.category;

import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryRequest;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryResponse;
import jakarta.validation.Valid;

public interface CategoryCreateUseCase {

  CategoryResponse execute(@Valid CategoryRequest request);
}

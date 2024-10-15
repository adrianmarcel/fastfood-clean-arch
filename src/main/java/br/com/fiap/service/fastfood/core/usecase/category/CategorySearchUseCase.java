package br.com.fiap.service.fastfood.core.usecase.category;

import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategorySearchUseCase {

  Page<CategoryResponse> execute(@Valid CategoryFilterRequest request, Pageable pageable);
}

package br.com.fiap.service.fastfood.entrypoint.rest.category;

import static org.springframework.http.ResponseEntity.*;

import br.com.fiap.service.fastfood.core.usecase.category.CategoryCreateUseCase;
import br.com.fiap.service.fastfood.core.usecase.category.CategoryDeleteUseCase;
import br.com.fiap.service.fastfood.core.usecase.category.CategorySearchUseCase;
import br.com.fiap.service.fastfood.core.usecase.category.CategoryUpdateUseCase;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryRequest;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryResponse;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryUpdateRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CategoryController.BASE_URI)
public class CategoryController {

  public static final String BASE_URI = "/v1/categories";

  private final CategoryCreateUseCase createUseCase;
  private final CategoryUpdateUseCase updateUseCase;
  private final CategoryDeleteUseCase deleteUseCase;
  private final CategorySearchUseCase searchUseCase;

  @PostMapping
  public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
    var response = createUseCase.execute(request);
    return created(URI.create(BASE_URI.concat("/").concat(response.getId().toString())))
        .body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryResponse> update(
      @PathVariable("id") UUID id, @Valid @RequestBody CategoryUpdateRequest request) {
    request.setId(id);
    return ok(updateUseCase.execute(request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
    deleteUseCase.execute(id);
    return noContent().build();
  }

  @GetMapping
  public ResponseEntity<Page<CategoryResponse>> search(
      @Valid CategoryFilterRequest filterRequest, Pageable pageable) {
    return ok(searchUseCase.execute(filterRequest, pageable));
  }
}

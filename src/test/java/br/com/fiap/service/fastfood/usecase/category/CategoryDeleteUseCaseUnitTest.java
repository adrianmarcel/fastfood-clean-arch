package br.com.fiap.service.fastfood.usecase.category;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.usecase.category.CategoryDeleteUseCase;
import br.com.fiap.service.fastfood.core.usecase.category.CategoryDeleteUseCaseImpl;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryFilterRequest;
import br.com.fiap.service.fastfood.fixtures.category.CategoryGatewayResponseFixture;
import br.com.fiap.service.fastfood.gateway.domain.category.CategoryDeleteGateway;
import br.com.fiap.service.fastfood.gateway.domain.category.CategorySearchGateway;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CategoryDeleteUseCaseUnitTest {

  private CategoryDeleteUseCase useCase;

  @Mock private CategoryDeleteGateway deleteGateway;
  @Mock private CategorySearchGateway searchGateway;

  @BeforeEach
  public void setUp() {
    useCase = new CategoryDeleteUseCaseImpl(deleteGateway, searchGateway);
  }

  @Test
  public void testCategoryDeleteWithErrorWhenCategoryNotFound() {
    when(searchGateway.execute(any(CategoryFilterRequest.class), any())).thenReturn(Page.empty());

    var be =
        assertThrows(
            BusinessException.class,
            () -> useCase.execute(UUID.fromString("a0ae2eeb-4fae-45e3-8a2d-40464d97a857")));

    assertEquals(ExceptionCode.CATEGORY_NOT_FOUND.getMessage(), be.getMessage());
    assertEquals(ExceptionCode.CATEGORY_NOT_FOUND.getCode().toString(), be.getCode());
  }

  @Test
  public void testCategoryDeleteWithSuccess() {
    when(searchGateway.execute(any(CategoryFilterRequest.class), any()))
        .thenReturn(new PageImpl<>(List.of(CategoryGatewayResponseFixture.validRequest())));

    doNothing().when(deleteGateway).execute(any(UUID.class));

    assertDoesNotThrow(() -> useCase.execute(UUID.randomUUID()));
  }
}

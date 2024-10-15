package br.com.fiap.service.fastfood.usecase.category;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.fiap.service.fastfood.core.usecase.category.CategoryCreateUseCase;
import br.com.fiap.service.fastfood.core.usecase.category.CategoryCreateUseCaseImpl;
import br.com.fiap.service.fastfood.fixtures.category.CategoryGatewayResponseFixture;
import br.com.fiap.service.fastfood.fixtures.category.CategoryRequestFixture;
import br.com.fiap.service.fastfood.gateway.domain.category.CategoryCreateGateway;
import br.com.fiap.service.fastfood.gateway.domain.category.model.CategoryGatewayRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CategoryCreateUseCaseUnitTest {

  private CategoryCreateUseCase useCase;

  @Mock private CategoryCreateGateway createGateway;

  @BeforeEach
  public void setUp() {
    useCase = new CategoryCreateUseCaseImpl(createGateway);
  }

  @Test
  public void testCategoryCreateWithSuccess() {
    when(createGateway.execute(any(CategoryGatewayRequest.class)))
        .thenReturn(CategoryGatewayResponseFixture.validRequest());

    assertDoesNotThrow(() -> useCase.execute(CategoryRequestFixture.validRequest()));
  }
}

package br.com.fiap.service.fastfood.entrypoint.rest.product;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.domain.shared.exception.FastfoodExceptionHandler;
import br.com.fiap.service.fastfood.core.usecase.product.ProductCreateUseCase;
import br.com.fiap.service.fastfood.core.usecase.product.ProductDeleteUseCase;
import br.com.fiap.service.fastfood.core.usecase.product.ProductSearchUseCase;
import br.com.fiap.service.fastfood.core.usecase.product.ProductUpdateUseCase;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductRequest;
import br.com.fiap.service.fastfood.core.usecase.product.model.ProductUpdateRequest;
import br.com.fiap.service.fastfood.fixtures.category.CategoryUpdateRequestFixture;
import br.com.fiap.service.fastfood.fixtures.product.ProductRequestFixture;
import br.com.fiap.service.fastfood.fixtures.product.ProductResponseFixture;
import br.com.fiap.service.fastfood.fixtures.product.ProductUpdateRequestFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@TestInstance(PER_CLASS)
@WebMvcTest(ProductController.class)
@Import(FastfoodExceptionHandler.class)
public class ProductControllerUnitTest {

  @Autowired private WebApplicationContext context;

  @Autowired @MockBean private ProductCreateUseCase createUseCase;
  @Autowired @MockBean private ProductUpdateUseCase updateUseCase;
  @Autowired @MockBean private ProductDeleteUseCase deleteUseCase;
  @Autowired @MockBean private ProductSearchUseCase searchUseCase;

  private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testCreateProductWithErrorWhenCategoryNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.CATEGORY_NOT_FOUND);
    when(createUseCase.execute(any(ProductRequest.class))).thenThrow(exception);

    mockMvc
        .perform(
            post("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProductRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testCreateProductWithSuccess() throws Exception {
    when(createUseCase.execute(any(ProductRequest.class)))
        .thenReturn(ProductResponseFixture.validResponse());

    mockMvc
        .perform(
            post("/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ProductRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.name").value("Product test"))
        .andExpect(jsonPath("$.category").value("919d8d5b-d72d-45a8-923b-86ed31a5e280"))
        .andExpect(jsonPath("$.amount").value(new BigDecimal("20.00").doubleValue()));
  }

  @Test
  public void testUpdateProductWithErrorWhenProductNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.PRODUCT_NOT_FOUND);
    when(updateUseCase.execute(any(ProductUpdateRequest.class))).thenThrow(exception);

    mockMvc
        .perform(
            put("/v1/products/1ae0b5fb-5f9e-4a64-b5fb-f89ba0ea2393")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(ProductUpdateRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testUpdateProductWithSuccess() throws Exception {
    when(updateUseCase.execute(any(ProductUpdateRequest.class)))
        .thenReturn(ProductResponseFixture.updatedResponse());

    mockMvc
        .perform(
            put("/v1/products/1cc73839-9b34-4158-9f93-789dc63a1cb2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(ProductUpdateRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.name").value("Product test"))
        .andExpect(jsonPath("$.category").value("919d8d5b-d72d-45a8-923b-86ed31a5e280"))
        .andExpect(jsonPath("$.amount").value(new BigDecimal("200.00").doubleValue()));
  }

  @Test
  public void testDeleteProductWithErrorWhenProductNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.PRODUCT_NOT_FOUND);
    doThrow(exception).when(deleteUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            delete("/v1/products/1ae0b5fb-5f9e-4a64-b5fb-f89ba0ea2393")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testDeleteProductWithSuccess() throws Exception {
    doNothing().when(deleteUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            delete("/v1/products/2776f1b5-ce4c-49e0-9691-ed07ddbc9130")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(CategoryUpdateRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNoContent());
  }

  @Test
  public void testSearchProductWithSuccess() throws Exception {
    when(searchUseCase.execute(any(ProductFilterRequest.class), any()))
        .thenReturn(new PageImpl<>(List.of(ProductResponseFixture.updatedResponse())));

    mockMvc
        .perform(
            get("/v1/products").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content[0].id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.content[0].name").value("Product test"))
        .andExpect(jsonPath("$.content[0].category").value("919d8d5b-d72d-45a8-923b-86ed31a5e280"))
        .andExpect(jsonPath("$.content[0].amount").value(new BigDecimal("200.00").doubleValue()));
  }
}

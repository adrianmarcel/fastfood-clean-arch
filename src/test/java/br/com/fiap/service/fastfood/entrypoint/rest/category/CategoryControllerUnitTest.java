package br.com.fiap.service.fastfood.entrypoint.rest.category;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.domain.shared.exception.FastfoodExceptionHandler;
import br.com.fiap.service.fastfood.core.usecase.category.CategoryCreateUseCase;
import br.com.fiap.service.fastfood.core.usecase.category.CategoryDeleteUseCase;
import br.com.fiap.service.fastfood.core.usecase.category.CategorySearchUseCase;
import br.com.fiap.service.fastfood.core.usecase.category.CategoryUpdateUseCase;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryRequest;
import br.com.fiap.service.fastfood.core.usecase.category.model.CategoryUpdateRequest;
import br.com.fiap.service.fastfood.fixtures.category.CategoryRequestFixture;
import br.com.fiap.service.fastfood.fixtures.category.CategoryResponseFixture;
import br.com.fiap.service.fastfood.fixtures.category.CategoryUpdateRequestFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@WebMvcTest(CategoryController.class)
@Import(FastfoodExceptionHandler.class)
public class CategoryControllerUnitTest {

  @Autowired private WebApplicationContext context;

  @Autowired @MockBean private CategoryCreateUseCase createUseCase;
  @Autowired @MockBean private CategoryDeleteUseCase deleteUseCase;
  @Autowired @MockBean private CategoryUpdateUseCase updateUseCase;
  @Autowired @MockBean private CategorySearchUseCase searchUseCase;

  private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testCreateCategoryWithSuccess() throws Exception {
    when(createUseCase.execute(any(CategoryRequest.class)))
        .thenReturn(CategoryResponseFixture.validResponse());

    mockMvc
        .perform(
            post("/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CategoryRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.name").value("Category test"));
  }

  @Test
  public void testUpdateCategoryWithErrorWhenCategoryNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.CATEGORY_NOT_FOUND);
    when(updateUseCase.execute(any(CategoryUpdateRequest.class))).thenThrow(exception);

    mockMvc
        .perform(
            put("/v1/categories/2776f1b5-ce4c-49e0-9691-ed07ddbc9130")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(CategoryUpdateRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testUpdateCategoryWithSuccess() throws Exception {
    when(updateUseCase.execute(any(CategoryUpdateRequest.class)))
        .thenReturn(CategoryResponseFixture.updatedResponse());

    mockMvc
        .perform(
            put("/v1/categories/1cc73839-9b34-4158-9f93-789dc63a1cb2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(CategoryUpdateRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.name").value("Category test 2"));
  }

  @Test
  public void testDeleteCategoryWithErrorWhenCategoryNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.CATEGORY_NOT_FOUND);
    doThrow(exception).when(deleteUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            delete("/v1/categories/2776f1b5-ce4c-49e0-9691-ed07ddbc9130")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(CategoryUpdateRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testDeleteCategoryWithSuccess() throws Exception {
    doNothing().when(deleteUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            delete("/v1/categories/2776f1b5-ce4c-49e0-9691-ed07ddbc9130")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    objectMapper.writeValueAsString(CategoryUpdateRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNoContent());
  }

  @Test
  public void testSearchCategoryWithSuccess() throws Exception {
    when(searchUseCase.execute(any(CategoryFilterRequest.class), any()))
        .thenReturn(new PageImpl<>(List.of(CategoryResponseFixture.updatedResponse())));

    mockMvc
        .perform(
            get("/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content[0].id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.content[0].name").value("Category test 2"));
  }
}

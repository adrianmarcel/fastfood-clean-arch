package br.com.fiap.service.fastfood.entrypoint.rest.customer;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.domain.shared.exception.FastfoodExceptionHandler;
import br.com.fiap.service.fastfood.core.usecase.customer.CustomerCreateUseCase;
import br.com.fiap.service.fastfood.core.usecase.customer.CustomerSearchUseCase;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerRequest;
import br.com.fiap.service.fastfood.fixtures.customer.CustomerRequestFixture;
import br.com.fiap.service.fastfood.fixtures.customer.CustomerResponseFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
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
@WebMvcTest(CustomerController.class)
@Import(FastfoodExceptionHandler.class)
public class CustomerControllerUnitTest {

  @Autowired private WebApplicationContext context;

  @Autowired @MockBean private CustomerCreateUseCase createUseCase;
  @Autowired @MockBean private CustomerSearchUseCase searchUseCase;

  private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testCreateCustomerWithSuccess() throws Exception {
    when(createUseCase.execute(any(CustomerRequest.class)))
        .thenReturn(CustomerResponseFixture.validResponse());

    mockMvc
        .perform(
            post("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CustomerRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.name").value("Customer test"))
        .andExpect(jsonPath("$.email").value("customer@test.com.br"))
        .andExpect(jsonPath("$.document").value("1234567901"));
  }

  @Test
  public void testCreateCustomerWithErrorWhenCustomerAlreadyExists() throws Exception {
    var exception = new BusinessException(ExceptionCode.CUSTOMER_ALREADY_EXISTS);
    when(createUseCase.execute(any(CustomerRequest.class))).thenThrow(exception);

    mockMvc
        .perform(
            post("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CustomerRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isUnprocessableEntity())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testIdentifyCustomerWithErrorWhenCustomerNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.CUSTOMER_NOT_FOUND);
    when(searchUseCase.execute(any(CustomerFilterRequest.class), any()))
        .thenReturn(new PageImpl<>(List.of()));

    mockMvc
        .perform(
            get("/v1/customers/12345678901")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testIdentifyCustomerWithSuccess() throws Exception {
    when(searchUseCase.execute(any(CustomerFilterRequest.class), any()))
        .thenReturn(new PageImpl<>(List.of(CustomerResponseFixture.validResponse())));

    mockMvc
        .perform(
            get("/v1/customers/12345678901")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .andExpect(jsonPath("$.name").value("Customer test"))
        .andExpect(jsonPath("$.email").value("customer@test.com.br"))
        .andExpect(jsonPath("$.document").value("1234567901"));
  }
}

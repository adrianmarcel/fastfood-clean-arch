package br.com.fiap.service.fastfood.entrypoint.rest.order;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.domain.shared.exception.FastfoodExceptionHandler;
import br.com.fiap.service.fastfood.core.usecase.order.OrderCreateUseCase;
import br.com.fiap.service.fastfood.core.usecase.order.OrderFinishUseCase;
import br.com.fiap.service.fastfood.core.usecase.order.OrderPulloutUseCase;
import br.com.fiap.service.fastfood.core.usecase.order.OrderSearchUseCase;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderRequest;
import br.com.fiap.service.fastfood.fixtures.order.OrderFilterRequestFixture;
import br.com.fiap.service.fastfood.fixtures.order.OrderRequestFixture;
import br.com.fiap.service.fastfood.fixtures.order.OrderResponseFixture;
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
@WebMvcTest(OrderController.class)
@Import(FastfoodExceptionHandler.class)
public class OrderControllerUnitTest {

  @Autowired private WebApplicationContext context;

  @Autowired @MockBean private OrderCreateUseCase createUseCase;
  @Autowired @MockBean private OrderSearchUseCase searchUseCase;
  @Autowired @MockBean private OrderFinishUseCase finishUseCase;
  @Autowired @MockBean private OrderPulloutUseCase pulloutUseCase;

  private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testCreateOrderWithErrorWhenProductNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.PRODUCT_NOT_FOUND);
    when(createUseCase.execute(any(OrderRequest.class))).thenThrow(exception);

    mockMvc
        .perform(
            post("/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(OrderRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testCreateOrderWithSuccess() throws Exception {
    when(createUseCase.execute(any(OrderRequest.class)))
        .thenReturn(OrderResponseFixture.validResponse());

    mockMvc
        .perform(
            post("/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(OrderRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("a6cfa873-1012-4596-a379-04ad2edab536"))
        .andExpect(jsonPath("$.customer").value("919d8d5b-d72d-45a8-923b-86ed31a5e280"))
        .andExpect(jsonPath("$.products[0].id").value("a6cfa873-1012-4596-a379-04ad2edab536"))
        .andExpect(jsonPath("$.products[0].quantity").value(1))
        .andExpect(jsonPath("$.status").value("Recebido/Aguardando pagamento"))
        .andExpect(
            jsonPath("$.mercadoPagoPaymentId").value("759b8987-9dcf-47f5-96e4-4be77018c4af"));
  }

  @Test
  public void testSearchOrderWithSuccess() throws Exception {
    when(searchUseCase.execute(any(OrderFilterRequest.class), any()))
        .thenReturn(new PageImpl<>(List.of(OrderResponseFixture.validResponse())));

    mockMvc
        .perform(
            get("/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(OrderFilterRequestFixture.validRequest()))
                .characterEncoding("UTF-8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content[0].id").value("a6cfa873-1012-4596-a379-04ad2edab536"))
        .andExpect(jsonPath("$.content[0].customer").value("919d8d5b-d72d-45a8-923b-86ed31a5e280"))
        .andExpect(
            jsonPath("$.content[0].products[0].id").value("a6cfa873-1012-4596-a379-04ad2edab536"))
        .andExpect(jsonPath("$.content[0].products[0].quantity").value(1))
        .andExpect(jsonPath("$.content[0].status").value("Recebido/Aguardando pagamento"))
        .andExpect(
            jsonPath("$.content[0].mercadoPagoPaymentId")
                .value("759b8987-9dcf-47f5-96e4-4be77018c4af"));
  }

  @Test
  public void testFinishOrderWithErrorWhenOrderNotPaid() throws Exception {
    var exception = new BusinessException(ExceptionCode.ORDER_NOT_PAID);
    doThrow(exception).when(finishUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            put("/v1/orders/a6cfa873-1012-4596-a379-04ad2edab536/finish")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isUnprocessableEntity())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testFinishOrderWithErrorWhenOrderNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.ORDER_NOT_FOUND);
    doThrow(exception).when(finishUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            put("/v1/orders/a6cfa873-1012-4596-a379-04ad2edab536/finish")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testFinishOrderWithSuccess() throws Exception {
    doNothing().when(finishUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            put("/v1/orders/a6cfa873-1012-4596-a379-04ad2edab536/finish")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNoContent());
  }

  @Test
  public void testPulloutOrderWithErrorWhenOrderNotReady() throws Exception {
    var exception = new BusinessException(ExceptionCode.ORDER_NOT_READY);
    doThrow(exception).when(pulloutUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            put("/v1/orders/a6cfa873-1012-4596-a379-04ad2edab536/pullout")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isUnprocessableEntity())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testPulloutOrderWithErrorWhenOrderNotFound() throws Exception {
    var exception = new BusinessException(ExceptionCode.ORDER_NOT_FOUND);
    doThrow(exception).when(pulloutUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            put("/v1/orders/a6cfa873-1012-4596-a379-04ad2edab536/pullout")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errors[0].code").value(exception.getCode()))
        .andExpect(jsonPath("$.errors[0].message").value(exception.getMessage()))
        .andExpect(jsonPath("$.code").value(exception.getHttpStatusCode().value()))
        .andExpect(jsonPath("$.message").value(exception.getHttpStatusCode().getReasonPhrase()));
  }

  @Test
  public void testPulloutOrderWithSuccess() throws Exception {
    doNothing().when(pulloutUseCase).execute(any(UUID.class));

    mockMvc
        .perform(
            put("/v1/orders/a6cfa873-1012-4596-a379-04ad2edab536/pullout")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
        .andExpect(status().isNoContent());
  }
}

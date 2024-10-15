package br.com.fiap.service.fastfood.core.usecase.order.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class OrderResponse {

  private UUID id;
  private UUID customer;
  private List<OrderProductRequest> products;
  private String status;
  private UUID mercadoPagoPaymentId;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  @SneakyThrows
  public static OrderResponse mapper(Object object) {
    var result = OrderResponse.builder().build();
    BeanUtils.copyProperties(object, result);

    return result;
  }
}

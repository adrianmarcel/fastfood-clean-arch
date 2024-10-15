package br.com.fiap.service.fastfood.gateway.domain.order.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderGatewayResponse {

  private UUID id;
  private UUID customer;
  private String products;
  private String status;
  private UUID mercadoPagoPaymentId;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public static OrderGatewayResponse mapper(Object object) {
    var result = OrderGatewayResponse.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}

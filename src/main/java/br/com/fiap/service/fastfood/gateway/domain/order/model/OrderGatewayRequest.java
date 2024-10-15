package br.com.fiap.service.fastfood.gateway.domain.order.model;

import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderGatewayRequest {

  private UUID id;
  private UUID customer;
  private String products;
  private String status;
  private UUID mercadoPagoPaymentId;

  public static OrderGatewayRequest mapper(Object object) {
    var result = OrderGatewayRequest.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}

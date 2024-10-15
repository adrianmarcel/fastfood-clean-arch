package br.com.fiap.service.fastfood.gateway.domain.product.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductGatewayRequest {

  private UUID id;
  private String name;
  private UUID category;
  private BigDecimal amount;

  public static ProductGatewayRequest mapper(Object object) {
    var result = ProductGatewayRequest.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}

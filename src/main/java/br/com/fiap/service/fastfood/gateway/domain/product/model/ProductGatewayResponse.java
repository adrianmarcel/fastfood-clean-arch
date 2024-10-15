package br.com.fiap.service.fastfood.gateway.domain.product.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductGatewayResponse {

  private UUID id;
  private String name;
  private UUID category;
  private BigDecimal amount;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public static ProductGatewayResponse mapper(Object object) {
    var result = ProductGatewayResponse.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}

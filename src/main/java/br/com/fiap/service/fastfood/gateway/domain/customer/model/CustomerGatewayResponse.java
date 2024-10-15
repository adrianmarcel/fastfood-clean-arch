package br.com.fiap.service.fastfood.gateway.domain.customer.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGatewayResponse {

  private UUID id;
  private String name;
  private String document;
  private OffsetDateTime createdAt;

  public static CustomerGatewayResponse mapper(Object object) {
    var result = CustomerGatewayResponse.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}

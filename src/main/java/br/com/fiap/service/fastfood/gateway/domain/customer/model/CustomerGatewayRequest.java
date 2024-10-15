package br.com.fiap.service.fastfood.gateway.domain.customer.model;

import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CustomerGatewayRequest {

  private UUID id;
  private String name;
  private String email;
  private String document;

  public static CustomerGatewayRequest mapper(Object object) {
    var result = CustomerGatewayRequest.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}

package br.com.fiap.service.fastfood.gateway.domain.category.model;

import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CategoryGatewayRequest {

  private UUID id;
  private String name;

  public static CategoryGatewayRequest mapper(Object object) {
    var result = CategoryGatewayRequest.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}

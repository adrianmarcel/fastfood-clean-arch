package br.com.fiap.service.fastfood.gateway.domain.category.model;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryGatewayResponse {

  private UUID id;
  private String name;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  public static CategoryGatewayResponse mapper(Object object) {
    var result = CategoryGatewayResponse.builder().build();
    BeanUtils.copyProperties(object, result);
    return result;
  }
}

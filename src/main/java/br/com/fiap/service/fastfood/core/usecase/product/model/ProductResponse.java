package br.com.fiap.service.fastfood.core.usecase.product.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class ProductResponse {

  private UUID id;
  private String name;
  private UUID category;
  private BigDecimal amount;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  @SneakyThrows
  public static ProductResponse mapper(Object object) {
    var result = ProductResponse.builder().build();
    BeanUtils.copyProperties(object, result);

    var name = PropertyUtils.getNestedProperty(object, "name");

    if (Objects.nonNull(name)) {
      result.setName(name.toString());
    }

    return result;
  }
}

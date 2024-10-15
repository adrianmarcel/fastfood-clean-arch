package br.com.fiap.service.fastfood.core.usecase.customer.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class CustomerResponse {

  private UUID id;
  private String name;
  private String email;
  private String document;
  private OffsetDateTime createdAt;

  @SneakyThrows
  public static CustomerResponse mapper(Object object) {
    var result = CustomerResponse.builder().build();
    BeanUtils.copyProperties(object, result);

    var name = PropertyUtils.getNestedProperty(object, "name");

    if (Objects.nonNull(name)) {
      result.setName(name.toString());
    }

    return result;
  }
}

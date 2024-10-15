package br.com.fiap.service.fastfood.core.usecase.category.model;

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
public class CategoryResponse {

  private UUID id;
  private String name;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  @SneakyThrows
  public static CategoryResponse mapper(Object object) {
    var result = CategoryResponse.builder().build();
    BeanUtils.copyProperties(object, result);

    var name = PropertyUtils.getNestedProperty(object, "name");

    if (Objects.nonNull(name)) {
      result.setName(name.toString());
    }

    return result;
  }
}

package br.com.fiap.service.fastfood.core.usecase.category.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryFilterRequest {

  @JsonIgnore private UUID id;
  private String name;
}

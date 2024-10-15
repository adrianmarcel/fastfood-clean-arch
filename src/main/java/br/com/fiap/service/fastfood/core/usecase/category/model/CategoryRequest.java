package br.com.fiap.service.fastfood.core.usecase.category.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryRequest {

  @JsonIgnore private UUID id;

  @NotBlank private String name;
}

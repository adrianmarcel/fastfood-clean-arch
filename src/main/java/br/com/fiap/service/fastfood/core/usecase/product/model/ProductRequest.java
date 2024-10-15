package br.com.fiap.service.fastfood.core.usecase.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductRequest {

  @JsonIgnore private UUID id;

  @NotBlank private String name;
  @NotNull private UUID category;
  @NotNull private BigDecimal amount;
}

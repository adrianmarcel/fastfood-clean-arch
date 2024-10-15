package br.com.fiap.service.fastfood.core.usecase.order.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderProductRequest {

  @NotNull private UUID id;

  @NotNull
  @Min(value = 1, message = "A quantidade deve ser maior que zero")
  private Integer quantity;
}

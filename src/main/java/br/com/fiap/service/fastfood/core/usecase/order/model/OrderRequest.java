package br.com.fiap.service.fastfood.core.usecase.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderRequest {

  @JsonIgnore private UUID id;

  private UUID customer;
  @Valid @NotNull private List<OrderProductRequest> products;
}

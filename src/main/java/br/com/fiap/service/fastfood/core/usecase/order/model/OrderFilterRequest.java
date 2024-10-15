package br.com.fiap.service.fastfood.core.usecase.order.model;

import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilterRequest {

  private UUID id;
  private UUID customerId;
  private OrderStatus status;
}

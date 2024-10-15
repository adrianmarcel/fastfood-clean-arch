package br.com.fiap.service.fastfood.core.usecase.customer.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFilterRequest {

  private String document;
}

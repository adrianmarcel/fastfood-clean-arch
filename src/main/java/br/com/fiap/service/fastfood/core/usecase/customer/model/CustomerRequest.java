package br.com.fiap.service.fastfood.core.usecase.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomerRequest {

  @JsonIgnore private UUID id;
  @NotBlank private String name;
  private String email;
  @NotBlank private String document;
}

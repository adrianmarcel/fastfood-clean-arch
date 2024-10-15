package br.com.fiap.service.fastfood.fixtures.customer;

import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerResponse;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CustomerResponseFixture {

  public static CustomerResponse validResponse() {
    return CustomerResponse.builder()
        .id(UUID.fromString("1cc73839-9b34-4158-9f93-789dc63a1cb2"))
        .name("Customer test")
        .email("customer@test.com.br")
        .document("1234567901")
        .createdAt(OffsetDateTime.now())
        .build();
  }
}

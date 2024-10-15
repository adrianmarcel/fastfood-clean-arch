package br.com.fiap.service.fastfood.fixtures.customer;

import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerRequest;

public class CustomerRequestFixture {

  public static CustomerRequest validRequest() {
    return CustomerRequest.builder()
        .name("Customer test")
        .email("customer@test.com.br")
        .document("1234567901")
        .build();
  }
}

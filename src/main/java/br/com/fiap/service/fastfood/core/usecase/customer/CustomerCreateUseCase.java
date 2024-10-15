package br.com.fiap.service.fastfood.core.usecase.customer;

import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerRequest;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerResponse;
import jakarta.validation.Valid;

public interface CustomerCreateUseCase {

  CustomerResponse execute(@Valid CustomerRequest request);
}

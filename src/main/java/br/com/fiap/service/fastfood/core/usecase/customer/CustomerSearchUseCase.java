package br.com.fiap.service.fastfood.core.usecase.customer;

import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerSearchUseCase {

  Page<CustomerResponse> execute(CustomerFilterRequest filterRequest, Pageable pageable);
}

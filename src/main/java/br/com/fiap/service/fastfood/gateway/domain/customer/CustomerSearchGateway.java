package br.com.fiap.service.fastfood.gateway.domain.customer;

import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerFilterRequest;
import br.com.fiap.service.fastfood.gateway.domain.customer.model.CustomerGatewayResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerSearchGateway {

  Page<CustomerGatewayResponse> execute(CustomerFilterRequest filterRequest, Pageable pageable);
}

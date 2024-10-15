package br.com.fiap.service.fastfood.gateway.domain.customer;

import br.com.fiap.service.fastfood.gateway.domain.customer.model.CustomerGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.customer.model.CustomerGatewayResponse;

public interface CustomerCreateGateway {

  CustomerGatewayResponse execute(CustomerGatewayRequest request);
}

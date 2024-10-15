package br.com.fiap.service.fastfood.gateway.database.customer;

import br.com.fiap.service.fastfood.gateway.database.customer.model.CustomerEntity;
import br.com.fiap.service.fastfood.gateway.database.customer.repository.CustomerRepository;
import br.com.fiap.service.fastfood.gateway.domain.customer.CustomerCreateGateway;
import br.com.fiap.service.fastfood.gateway.domain.customer.model.CustomerGatewayRequest;
import br.com.fiap.service.fastfood.gateway.domain.customer.model.CustomerGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCreateDBGatewayImpl implements CustomerCreateGateway {

  private final CustomerRepository repository;

  @Override
  public CustomerGatewayResponse execute(CustomerGatewayRequest request) {
    return CustomerGatewayResponse.mapper(repository.save(CustomerEntity.mapper(request)));
  }
}

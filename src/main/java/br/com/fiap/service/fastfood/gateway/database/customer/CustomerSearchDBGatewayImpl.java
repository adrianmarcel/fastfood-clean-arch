package br.com.fiap.service.fastfood.gateway.database.customer;

import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerFilterRequest;
import br.com.fiap.service.fastfood.gateway.database.customer.repository.CustomerRepository;
import br.com.fiap.service.fastfood.gateway.database.customer.specification.CustomerSpecification;
import br.com.fiap.service.fastfood.gateway.domain.customer.CustomerSearchGateway;
import br.com.fiap.service.fastfood.gateway.domain.customer.model.CustomerGatewayResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerSearchDBGatewayImpl implements CustomerSearchGateway {

  private final CustomerRepository repository;

  @Override
  public Page<CustomerGatewayResponse> execute(
      CustomerFilterRequest filterRequest, Pageable pageable) {
    return repository
        .findAll(CustomerSpecification.build(filterRequest), pageable)
        .map(CustomerGatewayResponse::mapper);
  }
}

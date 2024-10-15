package br.com.fiap.service.fastfood.core.usecase.customer;

import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerResponse;
import br.com.fiap.service.fastfood.gateway.domain.customer.CustomerSearchGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerSearchUseCaseImpl implements CustomerSearchUseCase {

  private final CustomerSearchGateway searchGateway;

  @Override
  public Page<CustomerResponse> execute(CustomerFilterRequest filterRequest, Pageable pageable) {
    return searchGateway.execute(filterRequest, pageable).map(CustomerResponse::mapper);
  }
}

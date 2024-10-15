package br.com.fiap.service.fastfood.core.usecase.customer;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerRequest;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerResponse;
import br.com.fiap.service.fastfood.gateway.domain.customer.CustomerCreateGateway;
import br.com.fiap.service.fastfood.gateway.domain.customer.CustomerSearchGateway;
import br.com.fiap.service.fastfood.gateway.domain.customer.model.CustomerGatewayRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerCreateUseCaseImpl implements CustomerCreateUseCase {

  private final CustomerCreateGateway createGateway;
  private final CustomerSearchGateway searchGateway;

  @Override
  public CustomerResponse execute(CustomerRequest request) {
    var customer =
        searchGateway
            .execute(
                CustomerFilterRequest.builder().document(request.getDocument()).build(),
                Pageable.unpaged())
            .stream()
            .findFirst();

    if (customer.isPresent()) {
      throw new BusinessException(ExceptionCode.CUSTOMER_ALREADY_EXISTS);
    }

    return CustomerResponse.mapper(createGateway.execute(CustomerGatewayRequest.mapper(request)));
  }
}

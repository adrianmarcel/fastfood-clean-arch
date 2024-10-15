package br.com.fiap.service.fastfood.entrypoint.rest.customer;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import br.com.fiap.service.fastfood.core.domain.shared.exception.BusinessException;
import br.com.fiap.service.fastfood.core.domain.shared.exception.ExceptionCode;
import br.com.fiap.service.fastfood.core.usecase.customer.CustomerCreateUseCase;
import br.com.fiap.service.fastfood.core.usecase.customer.CustomerSearchUseCase;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerRequest;
import br.com.fiap.service.fastfood.core.usecase.customer.model.CustomerResponse;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(CustomerController.BASE_URI)
public class CustomerController {

  public static final String BASE_URI = "/v1/customers";

  private final CustomerCreateUseCase createUseCase;
  private final CustomerSearchUseCase identifyUseCase;

  @PostMapping
  public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerRequest request) {
    var response = createUseCase.execute(request);
    return created(URI.create(BASE_URI.concat("/").concat(response.getId().toString())))
        .body(response);
  }

  @GetMapping("/{document}")
  public ResponseEntity<CustomerResponse> identify(@PathVariable("document") String document) {
    return ok(
        identifyUseCase
            .execute(CustomerFilterRequest.builder().document(document).build(), Pageable.unpaged())
            .stream()
            .findFirst()
            .orElseThrow(() -> new BusinessException(ExceptionCode.CUSTOMER_NOT_FOUND)));
  }
}

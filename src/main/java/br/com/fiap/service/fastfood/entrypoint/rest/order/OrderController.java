package br.com.fiap.service.fastfood.entrypoint.rest.order;

import static org.springframework.http.ResponseEntity.*;

import br.com.fiap.service.fastfood.core.usecase.order.OrderCreateUseCase;
import br.com.fiap.service.fastfood.core.usecase.order.OrderFinishUseCase;
import br.com.fiap.service.fastfood.core.usecase.order.OrderPulloutUseCase;
import br.com.fiap.service.fastfood.core.usecase.order.OrderSearchUseCase;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderFilterRequest;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderRequest;
import br.com.fiap.service.fastfood.core.usecase.order.model.OrderResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(OrderController.BASE_URI)
public class OrderController {

  public static final String BASE_URI = "/v1/orders";

  private final OrderCreateUseCase createUseCase;
  private final OrderSearchUseCase searchUseCase;
  private final OrderFinishUseCase finishUseCase;
  private final OrderPulloutUseCase pulloutUseCase;

  @PostMapping
  public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request) {
    var response = createUseCase.execute(request);
    return created(URI.create(BASE_URI.concat("/").concat(response.getId().toString())))
        .body(response);
  }

  @GetMapping
  public ResponseEntity<Page<OrderResponse>> search(
      @Valid OrderFilterRequest filterRequest, Pageable pageable) {
    return ok(searchUseCase.execute(filterRequest, pageable));
  }

  @PutMapping("/{id}/finish")
  public ResponseEntity<Void> finish(@PathVariable("id") UUID id) {
    finishUseCase.execute(id);
    return noContent().build();
  }

  @PutMapping("/{id}/pullout")
  public ResponseEntity<Void> pullout(@PathVariable("id") UUID id) {
    pulloutUseCase.execute(id);
    return noContent().build();
  }
}

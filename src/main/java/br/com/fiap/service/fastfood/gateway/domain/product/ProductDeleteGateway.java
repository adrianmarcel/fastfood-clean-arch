package br.com.fiap.service.fastfood.gateway.domain.product;

import java.util.UUID;

public interface ProductDeleteGateway {

  void execute(UUID id);
}

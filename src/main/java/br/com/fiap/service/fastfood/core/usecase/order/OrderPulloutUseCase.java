package br.com.fiap.service.fastfood.core.usecase.order;

import java.util.UUID;

public interface OrderPulloutUseCase {

  void execute(UUID id);
}

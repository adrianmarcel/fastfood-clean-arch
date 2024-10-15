package br.com.fiap.service.fastfood.core.usecase.order;

import java.util.UUID;

public interface OrderFinishUseCase {

  void execute(UUID id);
}

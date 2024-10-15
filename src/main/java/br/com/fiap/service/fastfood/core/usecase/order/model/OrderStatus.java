package br.com.fiap.service.fastfood.core.usecase.order.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
  RECEBIDO("Recebido/Aguardando pagamento"),
  PAGO("Pago/Em preparação"),
  PRONTO("Pronto/Aguardando retirada"),
  FINALIZADO("Retirado/Finalizado");

  private final String description;

  OrderStatus(String description) {
    this.description = description;
  }
}

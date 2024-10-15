package br.com.fiap.service.fastfood.core.domain.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
  PRODUCT_NOT_FOUND(1000, "Product not found.", HttpStatus.NOT_FOUND),
  CUSTOMER_NOT_FOUND(1001, "Customer not found.", HttpStatus.NOT_FOUND),
  CATEGORY_NOT_FOUND(1002, "Category not found.", HttpStatus.NOT_FOUND),
  ORDER_NOT_FOUND(1003, "Order not found.", HttpStatus.NOT_FOUND),
  ORDER_NOT_PAID(
      1004, "Order cannot be picked up without payment.", HttpStatus.UNPROCESSABLE_ENTITY),
  ORDER_NOT_READY(1005, "Order is not ready yet.", HttpStatus.UNPROCESSABLE_ENTITY),
  CUSTOMER_ALREADY_EXISTS(
      1006, "Customer already registered for this document.", HttpStatus.UNPROCESSABLE_ENTITY);

  private final Integer code;
  private final String message;
  private final HttpStatus httpStatus;

  ExceptionCode(Integer code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }
}

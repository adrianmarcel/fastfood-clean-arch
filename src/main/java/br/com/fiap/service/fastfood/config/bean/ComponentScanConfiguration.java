package br.com.fiap.service.fastfood.config.bean;

import br.com.fiap.service.fastfood.core.domain.shared.exception.FastfoodExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
  basePackages = "br.com.fiap",
  excludeFilters =
      @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        value = FastfoodExceptionHandler.class
      )
)
public class ComponentScanConfiguration {}

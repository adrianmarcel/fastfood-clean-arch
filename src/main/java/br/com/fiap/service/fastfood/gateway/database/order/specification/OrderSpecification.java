package br.com.fiap.service.fastfood.gateway.database.order.specification;

import br.com.fiap.service.fastfood.core.usecase.order.model.OrderFilterRequest;
import br.com.fiap.service.fastfood.gateway.database.order.model.OrderEntity;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;

public final class OrderSpecification {

  private OrderSpecification() {}

  public static Specification<OrderEntity> build(OrderFilterRequest filterRequest) {
    return (((root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (Objects.nonNull(filterRequest.getId())) {
        predicates.add(criteriaBuilder.equal(root.get("id"), filterRequest.getId()));
      }

      if (Objects.nonNull(filterRequest.getStatus())) {
        predicates.add(
            criteriaBuilder.equal(root.get("status"), filterRequest.getStatus().getDescription()));
      }

      return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }));
  }
}

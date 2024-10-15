package br.com.fiap.service.fastfood.gateway.database.order.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column private UUID customer;

  @Column private String products;

  @Column private String status;

  @Column private UUID mercadoPagoPaymentId;

  @CreationTimestamp
  @Column(updatable = false)
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  @Column(updatable = false)
  private OffsetDateTime updatedAt;

  public static OrderEntity mapper(Object object) {
    var result = OrderEntity.builder().build();
    BeanUtils.copyProperties(object, result);

    return result;
  }
}

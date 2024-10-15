package br.com.fiap.service.fastfood.gateway.database.product.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
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
@Table(name = "products")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column private String name;

  @Column private UUID category;

  @Column private BigDecimal amount;

  @CreationTimestamp
  @Column(updatable = false)
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  @Column(updatable = false)
  private OffsetDateTime updatedAt;

  public static ProductEntity mapper(Object object) {
    var result = ProductEntity.builder().build();
    BeanUtils.copyProperties(object, result);

    return result;
  }
}

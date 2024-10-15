package br.com.fiap.service.fastfood.gateway.database.customer.model;

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
@Table(name = "customers")
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column private String name;

  @Column private String email;

  @Column private String document;

  @CreationTimestamp
  @Column(updatable = false)
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  @Column(updatable = false)
  private OffsetDateTime updatedAt;

  public static CustomerEntity mapper(Object object) {
    var result = CustomerEntity.builder().build();
    BeanUtils.copyProperties(object, result);

    return result;
  }
}

package br.com.fiap.service.fastfood.gateway.database.customer.repository;

import br.com.fiap.service.fastfood.gateway.database.BaseRepository;
import br.com.fiap.service.fastfood.gateway.database.customer.model.CustomerEntity;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseRepository<CustomerEntity, UUID> {}

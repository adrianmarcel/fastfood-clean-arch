package br.com.fiap.service.fastfood.gateway.database.order.repository;

import br.com.fiap.service.fastfood.gateway.database.BaseRepository;
import br.com.fiap.service.fastfood.gateway.database.order.model.OrderEntity;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<OrderEntity, UUID> {}

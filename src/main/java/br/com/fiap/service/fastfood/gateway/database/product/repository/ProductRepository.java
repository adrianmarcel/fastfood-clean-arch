package br.com.fiap.service.fastfood.gateway.database.product.repository;

import br.com.fiap.service.fastfood.gateway.database.BaseRepository;
import br.com.fiap.service.fastfood.gateway.database.product.model.ProductEntity;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<ProductEntity, UUID> {}

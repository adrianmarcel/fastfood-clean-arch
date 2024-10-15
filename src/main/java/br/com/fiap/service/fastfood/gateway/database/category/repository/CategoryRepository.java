package br.com.fiap.service.fastfood.gateway.database.category.repository;

import br.com.fiap.service.fastfood.gateway.database.BaseRepository;
import br.com.fiap.service.fastfood.gateway.database.category.model.CategoryEntity;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<CategoryEntity, UUID> {}

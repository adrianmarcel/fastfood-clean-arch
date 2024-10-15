package br.com.fiap.service.fastfood.config.database;

import br.com.fiap.service.fastfood.gateway.database.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class, basePackages = "br.com.fiap")
public class JpaConfiguration {}

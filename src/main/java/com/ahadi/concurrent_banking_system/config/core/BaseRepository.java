package com.ahadi.concurrent_banking_system.config.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<Entity> extends JpaRepository<Entity,Long> {
}

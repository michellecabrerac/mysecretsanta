package com.michellecabrerac.mysecretsanta.repository;

import com.michellecabrerac.mysecretsanta.model.DrawConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDrawConfigurationRepository extends JpaRepository<DrawConfiguration, Long> {
}

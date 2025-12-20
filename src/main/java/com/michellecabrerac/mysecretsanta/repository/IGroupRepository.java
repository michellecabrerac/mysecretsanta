package com.michellecabrerac.mysecretsanta.repository;

import com.michellecabrerac.mysecretsanta.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGroupRepository extends JpaRepository<Group, Long> {
}

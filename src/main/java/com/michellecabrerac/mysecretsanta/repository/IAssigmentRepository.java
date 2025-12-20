package com.michellecabrerac.mysecretsanta.repository;

import com.michellecabrerac.mysecretsanta.model.Assigment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAssigmentRepository extends JpaRepository<Assigment, Long> {
}

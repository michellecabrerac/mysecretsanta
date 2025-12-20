package com.michellecabrerac.mysecretsanta.repository;

import com.michellecabrerac.mysecretsanta.model.DrawParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDrawRepository extends JpaRepository<DrawParticipant, Long> {
}

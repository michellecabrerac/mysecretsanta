package com.michellecabrerac.mysecretsanta.repository;

import com.michellecabrerac.mysecretsanta.model.DrawParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDrawParticipantRepository extends JpaRepository<DrawParticipant, Long> {
}

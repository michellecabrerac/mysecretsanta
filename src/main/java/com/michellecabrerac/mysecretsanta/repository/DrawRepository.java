package com.michellecabrerac.mysecretsanta.repository;

import com.michellecabrerac.mysecretsanta.model.DrawParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawRepository extends JpaRepository<DrawParticipant, Long> {
}

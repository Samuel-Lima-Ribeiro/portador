package com.client.portador.repository;

import com.client.portador.repository.entity.CardEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, UUID> {
    List<CardEntity> findByIdPortador(UUID idPortador);
}

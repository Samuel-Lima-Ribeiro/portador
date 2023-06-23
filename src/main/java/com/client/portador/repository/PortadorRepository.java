package com.client.portador.repository;

import com.client.portador.repository.entity.PortadorEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortadorRepository extends JpaRepository<PortadorEntity, UUID> {
}

package com.client.portador.repository;

import com.client.portador.repository.entity.PortadorEntity;
import com.client.portador.utils.Status;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortadorRepository extends JpaRepository<PortadorEntity, UUID> {
    List<PortadorEntity> getAllPortadoresByStatus(Status status);
}

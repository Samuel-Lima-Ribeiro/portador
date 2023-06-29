package com.client.portador.repository.entity;

import com.client.portador.utils.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "PORTADOR")
@Immutable
public class PortadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_portador")
    UUID id;

    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "limite")
    BigDecimal limit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_bancaria_id", referencedColumnName = "id")
    BankAccountEntity bankAccount;

    @Column(name = "cliente_id")
    UUID clientId;

    @Column(name = "analise_credito_id")
    UUID creditAnalysisId;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    private PortadorEntity() {
    }

    @Builder(toBuilder = true)
    public PortadorEntity(UUID id, Status status, BigDecimal limit, BankAccountEntity bankAccount, UUID clientId, UUID creditAnalysisId,
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.status = status;
        this.limit = limit;
        this.bankAccount = bankAccount;
        this.clientId = clientId;
        this.creditAnalysisId = creditAnalysisId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PortadorEntity{" + "id=" + id + ", status='"
                + status + '\'' + ", limit=" + limit + ", bankAccount=" + bankAccount + ", createdAt=" + createdAt + ", updateAt=" + updatedAt + '}';
    }

    public UUID getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BankAccountEntity getBankAccount() {
        return bankAccount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UUID getClientId() {
        return clientId;
    }

    public UUID getCreditAnalysisId() {
        return creditAnalysisId;
    }
}

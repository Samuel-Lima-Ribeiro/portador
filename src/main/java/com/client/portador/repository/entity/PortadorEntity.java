package com.client.portador.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "PORTADOR")
@Immutable
public class PortadorEntity {
    @Id
    @Column(name = "id_portador")
    UUID id;
    String status;
    @Column(name = "limite")
    Double limit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_bancaria_id", referencedColumnName = "id")
    BankAccountEntity bankAccount;

    @CreationTimestamp
    @Column(name = "createdAt")
    LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;

    private PortadorEntity() {
    }

    public PortadorEntity(String status, Double limit, BankAccountEntity bankAccount) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.limit = limit;
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "PortadorEntity{" + "id=" + id + ", status='"
                + status + '\'' + ", limit=" + limit + ", bankAccount=" + bankAccount + ", createdAt=" + createdAt + ", updateAt=" + updatedAt + '}';
    }

    public UUID getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Double getLimit() {
        return limit;
    }

    public BankAccountEntity getBankAccount() {
        return bankAccount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updatedAt;
    }
}

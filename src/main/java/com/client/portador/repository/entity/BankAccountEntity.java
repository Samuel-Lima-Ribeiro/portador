package com.client.portador.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "CONTA_BANCARIA")
@Immutable
public class BankAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String account;
    String agency;
    String bankCode;
    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    private BankAccountEntity() {
    }

    @Builder(toBuilder = true)
    public BankAccountEntity(UUID id, String account, String agency, String bankCode, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.account = account;
        this.agency = agency;
        this.bankCode = bankCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "BankAccountEntity{" + "id=" + id + ", account='" + account + '\'' + ", agency='"
                + agency + '\'' + ", bankCode='" + bankCode + '\'' + ", createdAt=" + createdAt + ", updateAt=" + updatedAt + '}';
    }

    public UUID getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getAgency() {
        return agency;
    }

    public String getBankCode() {
        return bankCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updatedAt;
    }
}

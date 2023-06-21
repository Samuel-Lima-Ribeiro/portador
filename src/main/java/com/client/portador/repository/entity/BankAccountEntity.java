package com.client.portador.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "CONTA_BANCARIA")
@Immutable
public class BankAccountEntity {
    @Id
    UUID id;
    String account;
    String agency;
    String bankCode;
    @CreationTimestamp
    @Column(name = "createdAt")
    LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updatedAt")
    LocalDateTime updatedAt;

    private BankAccountEntity() {
    }

    public BankAccountEntity(String account, String agency, String bankCode) {
        this.id = UUID.randomUUID();
        this.account = account;
        this.agency = agency;
        this.bankCode = bankCode;
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

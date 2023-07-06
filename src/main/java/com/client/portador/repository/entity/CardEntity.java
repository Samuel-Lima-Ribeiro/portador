package com.client.portador.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "CARTAO")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    UUID cardId;
    @Column(name = "limite")
    BigDecimal limit;
    String cardNumber;
    Integer cvv;
    LocalDate dueDate;
    @Column(name = "portador_id")
    UUID idPortador;
    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    private CardEntity() {
    }

    @Builder(toBuilder = true)
    public CardEntity(UUID cardId, BigDecimal limit, String cardNumber, Integer cvv,
                      LocalDate dueDate, UUID idPortador, LocalDateTime createdAt,
                      LocalDateTime updatedAt) {
        this.cardId = cardId;
        this.limit = limit;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dueDate = dueDate;
        this.idPortador = idPortador;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getCardId() {
        return cardId;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public UUID getIdPortador() {
        return idPortador;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

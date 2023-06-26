package com.client.portador.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CARTAO")
public class CardEntity {
    @Id
    @Column(name = "id")
    UUID cardId;
    // aq
    // mudar a referencia para limite
    @Column(name = "limite")
    BigDecimal limit;
    String cardNumber;
    Integer cvv;
    LocalDateTime dueDate;
    @Column(name = "portador_id")
    UUID idPortador;

    private CardEntity() {
    }

    public UUID getCardId() {
        return cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public UUID getIdPortador() {
        return idPortador;
    }

    public BigDecimal getLimit() {
        return limit;
    }
}

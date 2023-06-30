package com.client.portador.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;

@Entity
@Table(name = "CARTAO")
public class CardEntity {
    @Id
    @Column(name = "id")
    UUID cardId;
    @Column(name = "limite")
    BigDecimal limit;
    String cardNumber;
    Integer cvv;
    LocalDate dueDate;
    @Column(name = "portador_id")
    UUID idPortador;

    private CardEntity() {
    }

    @Builder
    public CardEntity(BigDecimal limit, String cardNumber, Integer cvv, LocalDate dueDate, UUID idPortador) {
        this.cardId = UUID.randomUUID();
        this.limit = limit;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.dueDate = dueDate;
        this.idPortador = idPortador;
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public UUID getIdPortador() {
        return idPortador;
    }

    public BigDecimal getLimit() {
        return limit;
    }
}

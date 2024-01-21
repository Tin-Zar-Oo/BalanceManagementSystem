package com.example.balancemanagement.domain.entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import jakarta.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class BalanceItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String item;
    private int unitPrice;
    private int quantity;

    @ManyToOne(optional = false)
    private Balance balance;

}
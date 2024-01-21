package com.example.balancemanagement.domain.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Balance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String category;
    private Type type;

    //no need to create balance obj without user & persist or merge have to do cascade
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User user;

    public enum Type {
        Income,
        Expense
    }

}
package com.example.balancemanagement.domain.entity;
import com.example.balancemanagement.domain.form.BalanceSummaryForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    @OneToMany(mappedBy = "balance", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<BalanceItem> items;

    public Balance(){
        items = new ArrayList<>();
    }

    public void addItem(BalanceItem item){
        item.setBalance(this);
        items.add(item);
    }

    public List<BalanceItem> getItems() {
        return items;
    }

    public void setItems(List<BalanceItem> items) {
        this.items = items;
    }

    //no need to create balance obj without user & persist or merge have to do cascade
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private User user;

    public enum Type {
        Income,
        Expense
    }

}
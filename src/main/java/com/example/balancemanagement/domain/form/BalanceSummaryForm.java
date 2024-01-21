package com.example.balancemanagement.domain.form;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class BalanceSummaryForm {
    private int id;
    private LocalDate date;
    private String category;

}
package com.capgemini.assignment.uiservice.model;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Account {
    private Long id;
    private Long customerId;
    private BigDecimal balance;
    private List<Transaction> transactions;
}

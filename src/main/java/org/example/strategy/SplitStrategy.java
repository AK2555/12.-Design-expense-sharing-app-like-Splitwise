package org.example.strategy;

import org.example.models.Expense;

import java.util.Map;

public interface SplitStrategy {
    Map<String,Double> calculate(Expense expense);
}

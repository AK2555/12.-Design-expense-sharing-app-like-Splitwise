package org.example.strategy;

import org.example.models.Expense;
import org.example.models.Split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy{
    @Override
    public Map<String, Double> calculate(Expense expense) {
        Map<String,Double> res=new HashMap<>();
        List<Split> splits=expense.getSplits();
        double total=0.0;
        for(Split split: splits){
            total+=split.getPaid();
        }
        double perPersonCost=total/splits.size();
        for(Split split: splits){
            res.put(split.getUser().getId(),split.getPaid()-perPersonCost);
        }
        return res;
    }
}

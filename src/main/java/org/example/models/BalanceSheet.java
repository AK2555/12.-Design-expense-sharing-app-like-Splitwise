package org.example.models;

import java.util.*;

public class BalanceSheet {
    private final Map<String,Double> balances=new HashMap<>();

    public void addUser(String id){
        balances.putIfAbsent(id,0.0);
    }

    public List<String> splitBalance(){
        PriorityQueue<UserBalance> debtors = new PriorityQueue<>();
        PriorityQueue<UserBalance> creditors = new PriorityQueue<>();

        for (String u : balances.keySet()) {
            double amt = balances.get(u);
            if (amt < 0) debtors.add(new UserBalance(u, -amt));
            else if (amt > 0) creditors.add(new UserBalance(u, amt));
        }

        List<String> result = new ArrayList<>();
        while (!debtors.isEmpty() && !creditors.isEmpty()) {
            UserBalance debtor = debtors.poll();
            UserBalance creditor = creditors.poll();

            double settle = Math.min(debtor.amount, creditor.amount);
            settle = Math.round(settle * 100.0) / 100.0;


            result.add(String.format("%s owes %s: %.2f", debtor.userId, creditor.userId, settle));

            debtor.amount -= settle;
            creditor.amount -= settle;

            if (debtor.amount > 0) debtors.add(debtor);
            if (creditor.amount > 0) creditors.add(creditor);
        }

        return result;
    }

    public void updateBalanceSheet(Map<String,Double> map){
        for(Map.Entry<String,Double> me: map.entrySet()){
            String key=me.getKey();
            Double amt=me.getValue();
           balances.put(key,balances.getOrDefault(key,0.0)+amt);
        }
    }
}

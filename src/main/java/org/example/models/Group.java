package org.example.models;

import org.example.strategy.SplitStrategy;

import java.util.*;

public class Group {
   private final String id;
   private final Map<String,User> members;
   private final BalanceSheet balanceSheet;
   private final SplitStrategy splitStrategy;
    private final Set<Integer> expenses;

   public Group(String id,SplitStrategy splitStrategy,BalanceSheet balanceSheet){
       this.id=id;
       this.splitStrategy=splitStrategy;
       this.members=new HashMap<>();
       this.balanceSheet=balanceSheet;
       this.expenses=new HashSet<>();
   }

   public void addMember(User user){
       members.put(user.getId(),user);
       balanceSheet.addUser(user.getId());
   }

   public void addExpense(Expense expense){
       if(expenses.contains(expense.getId())){
           return;
       }
       Map<String,Double> map=this.splitStrategy.calculate(expense);
       balanceSheet.updateBalanceSheet(map);
       expenses.add(expense.getId());
   }

   public List<String> getSplitValue(){
       return this.balanceSheet.splitBalance();
   }

   public boolean isUserPresent(String userId){
       return this.members.containsKey(userId);
   }

   public User getUserFromGroup(String userId){
       if(!isUserPresent(userId)){
           return null;
       }
       return members.get(userId);
   }

}

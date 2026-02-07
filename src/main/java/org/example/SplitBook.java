package org.example;

import org.example.models.User;

import java.util.ArrayList;
import java.util.List;

public class SplitBook {
    SplitWiseService splitWiseService;

    public SplitBook() {
       this.splitWiseService=new SplitWiseService();
       splitWiseService.createGroup("1","EQUAL");
    }

    public void registerUser(String userId, String displayName) {
       User user= splitWiseService.addUser(userId,displayName);
        splitWiseService.addUserToGroup("1",user);
    }

    public void recordExpense(int expenseId, List<String> members, List<Integer> paid) {
         splitWiseService.recordExpense("1",expenseId,members,paid);
    }

    public List<String> listBalances() {
        return splitWiseService.getSplitValue("1");
    }
}
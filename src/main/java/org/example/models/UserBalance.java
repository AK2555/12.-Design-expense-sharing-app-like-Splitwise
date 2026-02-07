package org.example.models;

public class UserBalance implements Comparable<UserBalance>{
    String userId;
    double amount;

    UserBalance(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }
    @Override
    public int compareTo(UserBalance o) {
        return this.userId.compareTo(o.userId);
    }
}

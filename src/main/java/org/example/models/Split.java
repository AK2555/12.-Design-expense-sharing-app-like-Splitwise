package org.example.models;

public class Split {
    private final User user;
    private final double paid;

    public Split(User user, double paid) {
        this.user = user;
        this.paid = paid;
    }

    public User getUser() {
        return user;
    }

    public double getPaid() {
        return paid;
    }
}

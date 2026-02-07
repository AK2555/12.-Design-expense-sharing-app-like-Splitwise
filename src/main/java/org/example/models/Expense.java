package org.example.models;

import java.util.List;

public class Expense {
    private final int id;
    private final List<Split> splits;

    public Expense(int id, List<Split> splits) {
        this.id = id;
        this.splits = splits;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public int getId() {
        return id;
    }
}

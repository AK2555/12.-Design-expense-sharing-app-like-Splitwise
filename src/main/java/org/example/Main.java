package org.example;


import jdk.jfr.Description;
import org.example.models.Expense;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        SplitBook sb = new SplitBook();
        sb.registerUser("A", "Ann");
        sb.registerUser("B", "Ben");

 //#1: [A,B], total=300, each owes 150
        sb.recordExpense(5, List.of("A", "B"), List.of(200, 0));




      //  Balances:
        List<String> res=  sb.listBalances();

        for(String str: res){
            System.out.println(str);
        }
        System.out.println();


        //#2: [B,C,D], total=90, each owes 30
        sb.recordExpense(5, List.of("A", "B"), List.of(0, 200));

        //  Balances:
        List<String> res1=  sb.listBalances();

        for(String str: res1){
            System.out.println(str);
        }
        System.out.println();

    }
}

//   ["A owes B: 150.00", "C owes B: 30.00", "D owes B: 30.00"]

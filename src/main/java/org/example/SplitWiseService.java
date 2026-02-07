package org.example;

import org.example.enums.SplitType;
import org.example.factory.SplitStrategyFactory;
import org.example.models.*;

import java.util.*;

public class SplitWiseService {
    private final Map<String, Group> groups;
    public SplitWiseService instance;
    private final SplitStrategyFactory factory;

    public SplitWiseService(){
        this.groups=new HashMap<>();
        this.factory=new SplitStrategyFactory();
    }

    public synchronized SplitWiseService getInstance(){
        if(instance==null){
            instance=new SplitWiseService();
        }
        return instance;
    }

    public User addUser(String id,String name){
        User user=new User(id,name);
         return user;
    }

    public Group createGroup(String id, String splitType){
         Group group=new Group(id,factory.getStrategy(SplitType.valueOf(splitType)),new BalanceSheet());
         groups.putIfAbsent(id,group);
         return group;
    }

    public void addUserToGroup(String groupId,User user){
           Group group=groups.get(groupId);
           if(group != null && user !=null && !group.isUserPresent(user.getId())){
               group.addMember(user);
           }
    }

    public void recordExpense(String groupId,int expenseId, List<String> members, List<Integer> paid){
       List<Split> splits=new ArrayList<>();
        Group group=groups.get(groupId);
        for(int i=0;i<members.size();i++){
            String userId=members.get(i);
            double amt=paid.get(i);
            Split split=new Split(group.getUserFromGroup(userId),amt);
            splits.add(split);
        }
        Expense expense=new Expense(expenseId,splits);


        for(String userId : members){
            if(!group.isUserPresent(userId)){
                return;
            }
        }

        group.addExpense(expense);
    }

    public List<String> getSplitValue(String groupId){
        return groups.get(groupId).getSplitValue();
    }



    public Group getGroup(String groupId){
           return groups.get(groupId);
    }
}

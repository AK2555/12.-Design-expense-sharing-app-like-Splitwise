package org.example.factory;

import org.example.enums.SplitType;
import org.example.strategy.EqualSplitStrategy;
import org.example.strategy.SplitStrategy;

public class SplitStrategyFactory {
    public SplitStrategy getStrategy(SplitType type){
        if(type == SplitType.EQUAL){
            return new EqualSplitStrategy();
        }
        return null;
    }
}

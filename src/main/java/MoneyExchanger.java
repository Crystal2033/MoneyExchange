import Exceptions.ImpossibleToExchange;
import Exceptions.WrongInputData;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MoneyExchanger {
    private final Map<Integer, Integer> exchangedSum = new HashMap<>();
    private final TreeSet<Integer> denominationValues;

    public MoneyExchanger(TreeSet<Integer> nominalValues) throws WrongInputData{
        this.denominationValues = (TreeSet<Integer>) nominalValues.descendingSet();
        if(denominationValues.stream().anyMatch(i-> i <=0)){
            throw new WrongInputData("Your denomination value should be > 0.");
        }
        this.denominationValues.forEach((nominal)-> exchangedSum.put(nominal, 0));
    }

    public Map<Integer, Integer> exchange(int totalSum) throws ImpossibleToExchange {
        makeExchangeAndInsertInMap(totalSum);
        return exchangedSum;
    }

    private void makeExchangeAndInsertInMap(int totalSum) throws ImpossibleToExchange {
        for(int denomination : denominationValues){
            int newMoneyValueStep = totalSum - denomination;
            if(newMoneyValueStep > 0){
                exchangedSum.put(denomination, exchangedSum.get(denomination) + 1);
                makeExchangeAndInsertInMap(newMoneyValueStep);
                return;
            }
            else if(newMoneyValueStep == 0){
                exchangedSum.put(denomination, exchangedSum.get(denomination) + 1);
                return;
            }
        }
            throw new ImpossibleToExchange("Impossible to exchange your money value.");
    }
}

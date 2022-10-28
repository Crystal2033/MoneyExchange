import Exceptions.ImpossibleToExchange;
import Exceptions.WrongInputData;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MoneyExchanger {
    private final Map<Integer, Integer> exchangedSum = new HashMap<>();
    private final TreeSet<Integer> denominationValues;

    public MoneyExchanger(TreeSet<Integer> nominalValues) throws WrongInputData {
        this.denominationValues = (TreeSet<Integer>) nominalValues.descendingSet();
        if (denominationValues.stream().anyMatch(i -> i <= 0)) {
            throw new WrongInputData("Your denomination value should be > 0.");
        }
        this.denominationValues.forEach((nominal) -> exchangedSum.put(nominal, 0));
    }

    public Map<Integer, Integer> exchange(int totalSum) throws ImpossibleToExchange {
        if (!makeExchangeAndInsertInMap(totalSum).equals(ExchangeStatus.EXCHANGED)) {
            throw new ImpossibleToExchange("Impossible to exchange your money value.");
        }
        return exchangedSum;
    }

    private ExchangeStatus makeExchangeAndInsertInMap(int totalSum){

        for (int denomination : denominationValues) {
            int newMoneyValueStep = totalSum - denomination;
            if (newMoneyValueStep > 0) {
                exchangedSum.put(denomination, exchangedSum.get(denomination) + 1);
                ExchangeStatus operationResult = makeExchangeAndInsertInMap(newMoneyValueStep);
                if (operationResult.equals(ExchangeStatus.EXCHANGED)) {
                    return ExchangeStatus.EXCHANGED;
                } else if (operationResult.equals(ExchangeStatus.BAD_INSERT)) {
                    exchangedSum.put(denomination, exchangedSum.get(denomination) - 1);
                }
            } else if (newMoneyValueStep == 0) {
                exchangedSum.put(denomination, exchangedSum.get(denomination) + 1);
                return ExchangeStatus.EXCHANGED;
            }
        }
        return ExchangeStatus.BAD_INSERT;
    }
}

import Exceptions.WrongInputData;
import MyCollections.Pair;

import java.util.TreeSet;

public class ArgsConverter {

    public Pair<Integer, TreeSet<Integer>> getMoneyAndDenominations(String[] args) throws NumberFormatException, WrongInputData {
        if (args.length < 2) {
            System.out.println("Value of args has to be more than two. First: MoneyValue. Second: list of denominations.");
            throw new WrongInputData("Not enough parameters.");
        }
        int moneyValue = Integer.parseInt(args[0]);
        TreeSet<Integer> denominationValues = new TreeSet<>();
        for (int i = 1; i < args.length; i++) {
            denominationValues.add(Integer.parseInt(args[i]));
        }
        return new Pair<>(moneyValue, denominationValues);
    }
}

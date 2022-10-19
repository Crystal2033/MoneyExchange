import Colors.ConsoleColors;
import Exceptions.ImpossibleToExchange;
import Exceptions.WrongInputData;
import MyCollections.Pair;
import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Application {
    public static void main(String[] args) {

        System.out.println("Developer: Kulikov Pavel, M8O-311");
        System.out.println("This program is able to exchange your money in denominations you want if it possible.");
        System.out.println();
        ArgsConverter argsConverter = new ArgsConverter();
        Pair<Integer, TreeSet<Integer>> moneyValueAndDenominations = null;
        try {
            moneyValueAndDenominations = argsConverter.getMoneyAndDenominations(args);
        }
        catch(NumberFormatException | WrongInputData numberFormatException){
            System.out.println(numberFormatException.getMessage());
            return;
        }

        try {
            if (moneyValueAndDenominations == null) throw new AssertionError();
            MoneyExchanger moneyExchanger = new MoneyExchanger(moneyValueAndDenominations.getSecond());
            Map<Integer, Integer> exchangedMoney =  moneyExchanger.exchange(moneyValueAndDenominations.getFirst());
            exchangedMoney.forEach((nominal, quantity)-> System.out.println(nominal + "[" + quantity + "]"));
        }
        catch (ImpossibleToExchange impossibleToExchange){
            System.out.println(impossibleToExchange.getMessage());
        }
        catch(WrongInputData wrongInputData){
            System.out.println(wrongInputData.getMessage());
        }
    }
}

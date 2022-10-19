import Exceptions.ImpossibleToExchange;
import Exceptions.WrongInputData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class ExchangeMoneyTests {
    @Nested
    @DisplayName("MoneyExchangerClass")
    class MoneyExchangerTests{
        @Test
        @DisplayName("exchangeMethod")
        void exchangeTest() throws WrongInputData, ImpossibleToExchange {
            Assertions.assertAll(
                    ()->{
                TreeSet<Integer> denominationValues = new TreeSet<>();
                denominationValues.add(4);
                denominationValues.add(2);
                denominationValues.add(1);
                MoneyExchanger moneyExchanger = new MoneyExchanger(denominationValues);

                Map<Integer, Integer> checkerMap = new HashMap<>();
                checkerMap.put(4, 2);
                checkerMap.put(2, 1);
                checkerMap.put(1, 0);
                Map<Integer, Integer> exchangedMoney = moneyExchanger.exchange(10);
                Assertions.assertEquals(checkerMap, exchangedMoney);
            },
                    ()->{
                TreeSet<Integer> denominationValues = new TreeSet<>();
                denominationValues.add(3);
                denominationValues.add(1);
                MoneyExchanger moneyExchanger = new MoneyExchanger(denominationValues);

                Map<Integer, Integer> checkerMap = new HashMap<>();
                checkerMap.put(3, 33);
                checkerMap.put(1, 1);
                Map<Integer, Integer> exchangedMoney = moneyExchanger.exchange(100);
                Assertions.assertEquals(checkerMap, exchangedMoney);
            },
                    ()->{
                TreeSet<Integer> denominationValues = new TreeSet<>();
                denominationValues.add(2);
                MoneyExchanger moneyExchanger = new MoneyExchanger(denominationValues);

                Map<Integer, Integer> checkerMap = new HashMap<>();
                checkerMap.put(2, 45);
                Map<Integer, Integer> exchangedMoney = moneyExchanger.exchange(90);
                Assertions.assertEquals(checkerMap, exchangedMoney);
                    });

        }

        @Test
        @DisplayName("exchangeTestExceptions")
        void exchangeTestExceptions() throws WrongInputData, ImpossibleToExchange {
            TreeSet<Integer> denominationValues = new TreeSet<>();
            denominationValues.add(4);
            denominationValues.add(2);
            denominationValues.add(-1);
            //MoneyExchanger moneyExchanger = new MoneyExchanger(denominationValues);
            Assertions.assertThrowsExactly(WrongInputData.class, ()-> new MoneyExchanger(denominationValues));

//            Map<Integer, Integer> checkerMap = new HashMap<>();
//            checkerMap.put(4, 2);
//            checkerMap.put(2, 1);
//            checkerMap.put(1, 0);
            //Map<Integer, Integer> exchangedMoney = moneyExchanger.exchange(10);
            //Assertions.assertEquals(checkerMap, exchangedMoney);

        }
    }
}

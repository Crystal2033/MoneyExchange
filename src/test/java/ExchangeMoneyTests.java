import Exceptions.ImpossibleToExchange;
import Exceptions.WrongInputData;
import MyCollections.Pair;
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
    class MoneyExchangerTests {
        @Test
        @DisplayName("exchangeMethod")
        void exchangeTest() throws WrongInputData, ImpossibleToExchange {
            Assertions.assertAll(
                    () -> {
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
                    () -> {
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
                    () -> {
                        TreeSet<Integer> denominationValues = new TreeSet<>();
                        denominationValues.add(2);
                        MoneyExchanger moneyExchanger = new MoneyExchanger(denominationValues);

                        Map<Integer, Integer> checkerMap = new HashMap<>();
                        checkerMap.put(2, 45);
                        Map<Integer, Integer> exchangedMoney = moneyExchanger.exchange(90);
                        Assertions.assertEquals(checkerMap, exchangedMoney);
                    },
                    () -> {
                        TreeSet<Integer> denominationValues = new TreeSet<>();
                        denominationValues.add(7);
                        denominationValues.add(1);
                        denominationValues.add(2);
                        MoneyExchanger moneyExchanger = new MoneyExchanger(denominationValues);

                        Map<Integer, Integer> checkerMap = new HashMap<>();
                        checkerMap.put(1, 1);
                        checkerMap.put(2, 1);
                        checkerMap.put(7, 8);
                        Map<Integer, Integer> exchangedMoney = moneyExchanger.exchange(59);
                        Assertions.assertEquals(checkerMap, exchangedMoney);
                    },
                    () -> {
                        TreeSet<Integer> denominationValues = new TreeSet<>();
                        denominationValues.add(7);
                        denominationValues.add(2);
                        denominationValues.add(2);
                        denominationValues.add(7);
                        MoneyExchanger moneyExchanger = new MoneyExchanger(denominationValues);

                        Map<Integer, Integer> checkerMap = new HashMap<>();
                        checkerMap.put(2, 1);
                        checkerMap.put(7, 8);
                        Map<Integer, Integer> exchangedMoney = moneyExchanger.exchange(58);
                        Assertions.assertEquals(checkerMap, exchangedMoney);
                    }
            );


        }

        @Test
        @DisplayName("exchangeTestExceptions")
        void exchangeTestExceptions() throws WrongInputData, ImpossibleToExchange {
            Assertions.assertAll(
                    () -> {
                        TreeSet<Integer> denominationValues = new TreeSet<>();
                        denominationValues.add(4);
                        denominationValues.add(2);
                        denominationValues.add(-1);
                        Assertions.assertThrowsExactly(WrongInputData.class, () -> new MoneyExchanger(denominationValues));
                    },
                    () -> {
                        TreeSet<Integer> denominationValues = new TreeSet<>();
                        denominationValues.add(4);
                        denominationValues.add(3);
                        MoneyExchanger moneyExchanger = new MoneyExchanger(denominationValues);

                        Assertions.assertThrowsExactly(ImpossibleToExchange.class, ()->{
                            Map<Integer, Integer> exchangedMoney = moneyExchanger.exchange(17);
                        });
                    });
        }
    }

    @Nested
    @DisplayName("ArgsConverterTestClass")
    class ArgsConverterTestClass{
        @Test
        @DisplayName("getMoneyAndDenominations")
        void getMoneyAndDenominations() {
            Assertions.assertAll(
                    ()->{
                        ArgsConverter argsConverter = new ArgsConverter();
                        Assertions.assertThrowsExactly(NumberFormatException.class, ()->{
                            argsConverter.getMoneyAndDenominations(new String[]{"asd", "2"});
                        });

                    },
                    ()->{
                        ArgsConverter argsConverter = new ArgsConverter();
                        Assertions.assertThrowsExactly(NumberFormatException.class, ()->{
                            argsConverter.getMoneyAndDenominations(new String[]{"123", "23", "2["});
                        });

                    },
                    ()->{
                        ArgsConverter argsConverter = new ArgsConverter();
                        Assertions.assertThrowsExactly(WrongInputData.class, ()->{
                            argsConverter.getMoneyAndDenominations(new String[]{"123"});
                        });

                    });

        }
    }
}

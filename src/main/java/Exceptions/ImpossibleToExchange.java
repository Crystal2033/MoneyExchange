package Exceptions;

import Colors.ConsoleColors;

public class ImpossibleToExchange extends Exception{
    private final String errorMessage;
    public ImpossibleToExchange(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage(){
        return errorMessage;
    }
}

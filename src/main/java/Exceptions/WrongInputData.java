package Exceptions;

import Colors.ConsoleColors;

public class WrongInputData extends Exception {
    private final String errorMessage;

    public WrongInputData(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}

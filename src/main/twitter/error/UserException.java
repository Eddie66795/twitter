package main.twitter.error;

public class UserException extends Exception {

    String errorMessage;

    String HelloThere = "THIS WORKS";

    public UserException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}

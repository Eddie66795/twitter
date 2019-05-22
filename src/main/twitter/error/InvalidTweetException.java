package main.twitter.error;

public class InvalidTweetException extends Exception {

    String errorMessage;

    public InvalidTweetException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}

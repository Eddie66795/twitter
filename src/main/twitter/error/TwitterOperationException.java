package main.twitter.error;

public class TwitterOperationException extends Exception {

    String errorMessage;

    public TwitterOperationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}

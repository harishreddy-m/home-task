package harish.task.app.exception;

public class FailedTransaction extends RuntimeException{

    private String errorMessage;

    public FailedTransaction(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

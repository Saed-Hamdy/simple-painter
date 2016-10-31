package exceptions;

/**
 * Created by ahmedyakout on 10/29/16.
 */
public class HistoryIsEmptyException extends Exception {
    @Override
    public String toString() {
        return "Invalid operation: History is empty!";
    }
}

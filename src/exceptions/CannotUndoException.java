package exceptions;

/**
 * Created by ahmedyakout on 10/29/16.
 */
public class CannotUndoException extends Exception {
    @Override
    public String toString() {
        return "Invalid operation: nothing to be undone!";
    }
}
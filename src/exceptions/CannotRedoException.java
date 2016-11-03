package exceptions;

/**
 * Class Redo exception
 * Created by YS team
 * 
 */
public class CannotRedoException extends Exception{
    @Override
    
    public String toString() {
        return "Invalid operation: nothing to be redone!";
    }
}

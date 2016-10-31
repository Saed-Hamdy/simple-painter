package mvc.model;

import exceptions.CannotRedoException;
import exceptions.CannotUndoException;
import exceptions.HistoryIsEmptyException;
import shapes.Shape;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Created by ahmedyakout on 10/29/16.
 */
public class History {
    private final int MAX_INITIAL_LIMIT = 10;
    private int limit;
    public Stack<List<Shape>> primaryHistoryStack;
    public Stack<List<Shape>> secondaryHistoryStack;

    public History() {
        primaryHistoryStack = new Stack<>();
        primaryHistoryStack.push(new ArrayList<>());
        secondaryHistoryStack = new Stack<>();
        limit = MAX_INITIAL_LIMIT;
    }

    public void redo() throws CannotRedoException, HistoryIsEmptyException {
        if (secondaryHistoryStack.isEmpty() && primaryHistoryStack.isEmpty()) {
            throw new HistoryIsEmptyException();
        } else {
            try {
                List<Shape> shapes = primaryHistoryStack.push(secondaryHistoryStack.pop());
                Model.getModel().setShapes(Model.getModel().cloneShapes(shapes));
            } catch (EmptyStackException e) {
                throw new CannotRedoException();
            }
        }

    }

    public void undo() throws CannotUndoException, HistoryIsEmptyException{
        if (secondaryHistoryStack.isEmpty() && primaryHistoryStack.isEmpty()) {
            throw new HistoryIsEmptyException();
        } else {
            try {
                secondaryHistoryStack.push(primaryHistoryStack.pop());
                List<Shape> shapes = primaryHistoryStack.peek();
                Model.getModel().setShapes(Model.getModel().cloneShapes(shapes));
            } catch (EmptyStackException e) {
                throw new CannotUndoException();
            }
        }
    }

    public void update() {
        // like taking a snapshot of the shapes array state and push it in the history
        primaryHistoryStack.push(Model.getModel().cloneShapes(Model.getModel().getShapes()));
        secondaryHistoryStack.clear();
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

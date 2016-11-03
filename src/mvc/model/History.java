package mvc.model;

import exceptions.CannotRedoException;
import exceptions.CannotUndoException;
import exceptions.HistoryIsEmptyException;
import mvc.controller.MainGuiController;
import shapes.Shape;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * Created by ahmedyakout on 10/29/16.
 */
public class History {
    private final int MAX_HISTORY_LIMIT = 20;
    private int limit;
    public Stack<List<Shape>> primaryHistoryStack;
    public Stack<List<Shape>> secondaryHistoryStack;

    public History() {
        primaryHistoryStack = new Stack<>();
        primaryHistoryStack.push(new ArrayList<Shape>());
        secondaryHistoryStack = new Stack<>();
        limit = MAX_HISTORY_LIMIT;
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

    /**
     * update the history with last state (snapshot) of the array of shapes in the model
     */
    public void update() {
        // if history limit is reached
        if (primaryHistoryStack.size() >= limit) {
            primaryHistoryStack.remove(0);
        }
        // like taking a snapshot of the shapes array state and push it in the history
        primaryHistoryStack.push(Model.getModel().cloneShapes(Model.getModel().getShapes()));
        secondaryHistoryStack.clear();

        if (MainGuiController.getMainGuiController().getClient() != null) {
            MainGuiController.getMainGuiController().sendShapes();
        }
    }

    /**
     *
     * @return history limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * get the limit value from user by showing an input dialog
     * @param limit the limit from the user.
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }
}

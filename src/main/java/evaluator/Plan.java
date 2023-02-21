package evaluator;

import java.util.Iterator;
import java.util.LinkedList;

public class Plan {
    private final LinkedList<Statement> listOfState = new LinkedList<>();
    private Iterator<Statement> iterator = listOfState.iterator();

    protected void addState(Statement statement) {
        listOfState.add(statement);
    }

    public Statement nextState() {
        return hasNext() ? iterator.next() : null;
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public void resetIterator() {
        iterator = listOfState.iterator();
    }
}

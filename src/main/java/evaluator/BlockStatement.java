package evaluator;

import java.util.LinkedList;

public class BlockStatement implements Statement {
    private LinkedList<Statement> blockStatelist;

    public BlockStatement(LinkedList<Statement> list) {
        this.blockStatelist = list;
    }

    public LinkedList<Statement> getList() {
        return blockStatelist;
    }

    @Override
    public String val() throws SyntaxError {
        return null;
    }
}

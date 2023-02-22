package evaluator;

import java.util.LinkedList;

public class BlockStatement implements Statement {
    private LinkedList<Statement> statelist;

    public BlockStatement(LinkedList<Statement> list) {
        this.statelist = list;
    }

    public LinkedList<Statement> getList() {
        return statelist;
    }

    @Override
    public long evaluate() throws SyntaxError {
        for (Statement s : statelist) {
            s.evaluate();
        }
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("Blockstatement ");
        return s;
    }
}


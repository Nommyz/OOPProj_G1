package evaluator;

public class AssignStatement implements Statement {
    private Identifier identifier;
    private String op;
    private Statement expression;

    public AssignStatement(Identifier identifier, String op, Statement expression) {
        this.identifier = identifier;
        this.op = op;
        this.expression = expression;
    }

    public Identifier Identifier() {
        return identifier;
    }

    public String Op() {
        return op;
    }

    public Statement Expression() {
        return expression;
    }

    @Override
    public long evaluate() throws SyntaxError {
        String stringidentifiers = Identifier().value();
        if (!stringidentifiers.equals("random")) {
            if (!identifier.variables.containsKey(stringidentifiers)) {
                identifier.variables.put(stringidentifiers, 0L);
            }
            long val = Expression().evaluate();
            identifier.variables.put(stringidentifiers, val);
        }
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("AssignStatement ");
        return s;
    }
}

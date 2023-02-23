package evaluator;

public class AssignStatement implements Statement {
    private Identifier identifier;
    private String op;
    private Statement expression;
    public VariableStorage variableStorage;
    public AssignStatement(Identifier identifier, String op, Statement expression,VariableStorage variableStorage) {
        this.identifier = identifier;
        this.op = op;
        this.expression = expression;
        this.variableStorage = variableStorage;
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
        String stringidentifiers = identifier.value();
        if (!stringidentifiers.equals("random")) {
            if (!variableStorage.variables.containsKey(stringidentifiers)) {
                variableStorage.variables.put(stringidentifiers, 0L);
            }
            long val = Expression().evaluate();
            variableStorage.variables.put(stringidentifiers, val);
        }
        return 0;
    }

    @Override
    public StringBuilder addCommand(StringBuilder s) {
        s.append("AssignStatement ");
        return s;
    }
}

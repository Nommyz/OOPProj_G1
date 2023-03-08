package evaluator;

import data.Unit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Parser {
    private Tokenizer token;
    private Plan plan;
    Unit crew;
    VariableStorage variableStorage = new VariableStorage();
    private static final Set<String> reservedWords = new HashSet<>(Arrays.asList(
            "collect", "done", "invest", "opponent", "relocate",
            "down", "downleft", "downright",
            "up", "upleft", "upright",
            "while", "else", "if", "shoot", "then", "move", "nearby"
    ));

    public String parse(String stream,Unit crew) throws SyntaxError {
        this.token = new Tokenizer(stream);
        this.crew = crew;
        plan = parsePlan();
        return plan.evaluate();
    }

    private Plan parsePlan() throws SyntaxError {
        Plan plan = new Plan();
        while (!token.empty()) {
            plan.addState(parseStatement());
        }
        return plan;
    }

    private Statement parseStatement() throws SyntaxError {
        return switch (token.peek()) {
            case "{" -> parseBlockStatement();//BlockStatement
            case "if" -> parseIfStatement();            //ifStatement
            case "while" -> parseWhileStatement();      // WhileStatement
            default -> parseCommand();                  //Command
        };
    }

//    /**
//     * Command  AssignmentStatement | ActionCommand
//     */
    private Statement parseCommand() throws SyntaxError {
        if (token.peek("done") || token.peek("relocate") || token.peek("move") || token.peek("invest") || token.peek("collect") || token.peek("shoot")) {
            return parseActionCommand();
        } else {
            return parseAssignmentStatement();
        }
    }

//    /**
//     * AssignmentStatement  <identifier> = Expression
//     */
    private Statement parseAssignmentStatement() throws SyntaxError {
        Identifier identifier = parseIdentifier();
        token.consume("=");
        Statement expression = parseExpression();
        return new AssignStatement(identifier, "=", expression,variableStorage);
    }

//    /**
//     * ActionCommand  done | relocate | MoveCommand(move) | RegionCommand | AttackCommand(shoot)
//     * */
    private Statement parseActionCommand() throws SyntaxError {
        return switch (token.peek()) {
            case "move" -> parseMoveCommand();
            case "shoot" -> parseAttackCommand();
            case "invest" -> parseRegionCommand();
            case "collect" -> parseRegionCommand();
            case "done" -> parseDoneCommand();
            case "relocate" -> parseRelocateCommand();
            default -> throw new SyntaxError("Error");
        };

    }
    private Statement parseDoneCommand() throws SyntaxError {
        if (token.peek("done")) {
            token.consume();
            return new ActionCommand("done",crew);
        }else throw new SyntaxError("ERROR");

    }
    private Statement parseRelocateCommand() throws SyntaxError {
        if (token.peek("relocate")) {
            token.consume();
            return new ActionCommand("relocate",crew);
        }else throw new SyntaxError("Error");
    }
//    /**
//     * MoveCommand  move Direction
//     */
    private Statement parseMoveCommand() throws SyntaxError {
        if (token.peek("move")) {
            token.consume();
            return new ActionCommand("move", parseDirection(),crew);
        } else throw new SyntaxError("Error");
    }

    private Statement parseRegionCommand() throws SyntaxError {
        if (token.peek("invest")) {
            token.consume();
            return new ActionCommand("invest", parseExpression(),crew);
        } else if (token.peek("collect")) {
            token.consume();
            return new ActionCommand("collect", parseExpression(),crew);
        } else throw new SyntaxError("Error");
    }

//    /**
//     * AttackCommand  shoot Direction
//     */
    private Statement parseAttackCommand() throws SyntaxError {
        if (token.peek("shoot")) {
            token.consume();
            return new ActionCommand("shoot", parseDirection(),parseExpression(),crew);
        } else throw new SyntaxError("Error");
    }

//    /**
//     Expression  Expression + Term | Expression - Term | Term
//     */
    private Statement parseExpression() throws SyntaxError {
        Statement term = parseTerm();
        while (token.peek("+") || token.peek("-")) {
            switch (token.peek()) {
                case "+" -> {
                    token.consume();
                    term = new Expr(term, "+", parseTerm());
                }
                case "-" -> {
                    token.consume();
                    term = new Expr(term, "-", parseTerm());
                }
                default -> throw new SyntaxError("Error");
            }
        }
        return term;
    }

//    /**
//     Term  Term * Factor | Term / Factor | Term % Factor | Factor
//     */
    private Statement parseTerm() throws SyntaxError {
        Statement factor = parseFactor();
        while (token.peek("*") || token.peek("/") || token.peek("%")) {
            switch (token.peek()) {
                case "*" -> {
                    token.consume();
                    factor = new Expr(factor, "*", parseFactor());
                }
                case "/" -> {
                    token.consume();
                    factor = new Expr(factor, "/", parseFactor());
                }
                case "%" -> {
                    token.consume();
                    factor = new Expr(factor, "%", parseFactor());
                }
                default -> throw new SyntaxError("Error");
            }
        }
        return factor;
    }

//    /**
//     * Factor  Power ^ Factor |   Power
//     */
    private Statement parseFactor() throws SyntaxError {
        Statement power = parsePower();
        if (token.peek("^")) {
            token.consume("^");
            power = new Expr(power, "^", parseFactor());
        }
        return power;

    }

//    /**
//     * Power  <number> | <identifier> |  (Expression) | InfoExpression
//     */
    private Statement parsePower() throws SyntaxError {
        if (token.isNumber(token.peek())) {
            return new LongIntLit(Integer.parseInt(token.consume()));
        } else if (token.peek("(")) {
            token.consume("(");
            Statement expression = parseExpression();
            token.consume(")");
            return expression;
        } else if (token.peek("opponent") || token.peek("nearby")) {
            return parseInfoExpression();
        } else {
            return parseIdentifier();
        }
    }

//    /**
//     * Identifier
//     */
    private Identifier parseIdentifier() throws SyntaxError {
        if (reservedWords.contains(token.peek())) {
            token.consume();
            throw new SyntaxError("Error");
        }
        if (!token.isNumber("" + token.peek().charAt(0))) {
            if (token.peek().substring(1).chars().allMatch(Character::isLetterOrDigit)) {
                return new Identifier(token.consume(),variableStorage);
            }
        }
        throw new SyntaxError("Error");
    }

//    /**
//     * InfoExpression  opponent | nearby Direction
//     */
    private Statement parseInfoExpression() throws SyntaxError {
        switch (token.peek()) {
            case "opponent":
                token.consume();
                return new InfoExpr("opponent",crew);
            case "nearby":
                token.consume();
                Direction direction = parseDirection();
                return new InfoExpr("nearby", direction,crew);
            default:
                throw new SyntaxError("Error");
        }
    }

    /**
     * Direction  return direction
     */
    private Direction parseDirection() throws SyntaxError {
        Direction direction = Direction.getDirection(token.peek());
        token.consume();
        if (direction != null) {
            return direction;
        } else {
            throw new SyntaxError("Error");
        }
    }

//    /**
//     * BlockStatement  { Statement* }
//     */
    private BlockStatement parseBlockStatement() throws SyntaxError {
        token.consume("{");

        LinkedList<Statement> state = new LinkedList<>();
        while (!token.peek("}")) {
            state.add(parseStatement());
        }

        token.consume("}");
        return new BlockStatement(state);
    }

    /**
     * ifStatement  if (Expression) then statement  else statement
     */
    private Statement parseIfStatement() throws SyntaxError {
        token.consume("if");
        token.consume("(");
        Statement Expression = parseExpression();
        token.consume(")");
        token.consume("then");
        Statement trueStatement = parseStatement();
        token.consume("else");
        Statement falseStatement = parseStatement();
        return new IfStatement(Expression, trueStatement, falseStatement);

    }

    /**
     * WhileStatement  while ( Expression ) Statement
     */
    private Statement parseWhileStatement() throws SyntaxError {
        token.consume("while");
        Statement Expression = parseExpression();
        Statement trueStatement = parseStatement();
        return new WhileStatement(Expression, trueStatement);
    }
}

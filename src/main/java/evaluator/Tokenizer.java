package evaluator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class Tokenizer {
    private static final Pattern splitPattern =
            Pattern.compile("([\\s]++)|(?<=[=+-\\-*/%(){}^])|(?=[=+\\-*/%(){}^])");

    private Queue<String> tokenlist;

    public Tokenizer(String stream) {
        tokenlist = new LinkedList<>();
        String[] separated = splitPattern.split(stream);
        for (String s : separated) {
            if (!s.trim().isEmpty()) {
                tokenlist.add(s);
            }
        }
    }

    public String peek() throws SyntaxError {
        if (!tokenlist.isEmpty()) {
            return tokenlist.element();
        } else throw new SyntaxError("Empty");
    }

    public boolean peek(String s) throws SyntaxError {
        return peek().equals(s);
    }

    public String consume() {
        return tokenlist.remove();
    }

    public void consume(String s) throws SyntaxError {
        if (peek(s)) {
            consume();
        } else throw new SyntaxError("Error");
    }

    public boolean empty() {
        return tokenlist.isEmpty();
    }

    public static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

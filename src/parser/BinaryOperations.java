package parser;

import java.util.HashMap;
import java.util.Map;

public enum BinaryOperations {
    CONJUNCTION,
    DISJUNCTION,
    IMPLICATION,
    VAR;

    private static final Map<BinaryOperations, String> symbols;

    static {
        symbols = new HashMap<>();
        symbols.put(CONJUNCTION, "&");
        symbols.put(DISJUNCTION, "|");
        symbols.put(IMPLICATION, "->");
    }

    private static final Map<BinaryOperations, Integer> priorities;

    static {
        priorities = new HashMap<>();
        priorities.put(IMPLICATION, 0);
        priorities.put(DISJUNCTION, 1);
        priorities.put(CONJUNCTION, 2);
    }

    public int getPriority() {
        return priorities.get(this);
    }



    @Override
    public String toString() {
        return symbols.get(this);
    }
}
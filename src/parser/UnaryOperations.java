package parser;

import java.util.HashMap;
import java.util.Map;

public enum UnaryOperations {
    NEGATION;

    private static final Map<UnaryOperations, String> symbols;

    static {
        symbols = new HashMap<>();
        symbols.put(NEGATION, "!");
    }


    @Override
    public String toString() {
        return symbols.get(this);
    }
}

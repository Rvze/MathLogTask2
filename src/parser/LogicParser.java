package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LogicParser {
    private String expr;
    private final ArrayList<StateImpl> states = new ArrayList<>();
    private final StateImpl state = new StateImpl("", false);
    private static final Map<BinaryOperations, Character> MAP;

    static {
        MAP = new HashMap<>();
        MAP.put(BinaryOperations.CONJUNCTION, '&');
        MAP.put(BinaryOperations.DISJUNCTION, '|');
        MAP.put(BinaryOperations.IMPLICATION, '-');
    }


    Tree expression() {
        Tree left = con();
        if (expr.charAt(state.getPos()) == MAP.get(BinaryOperations.DISJUNCTION)) {
            state.setPos(state.getPos() + 1);
            Tree right = expression();
            return new Tree(left, right, new StateImpl("", false), BinaryOperations.DISJUNCTION, false);
        }
        return left;
    }

    Tree con() {
        Tree left = parseNeg();
        if (expr.charAt(state.getPos()) == MAP.get(BinaryOperations.CONJUNCTION)) {
            state.setPos(state.getPos() + 1);
            Tree right = con();
            return new Tree(left, right, new StateImpl("", false), BinaryOperations.CONJUNCTION, false);
        }
        return left;
    }

    Tree impl() {
        Tree left = expression();
        if (expr.charAt(state.getPos()) == MAP.get(BinaryOperations.IMPLICATION)) {
            state.setPos(state.getPos() + 2);
            Tree right = impl();
            return new Tree(left, right, new StateImpl("", false), BinaryOperations.IMPLICATION, false);
        }
        return left;
    }

    StateImpl parseState(String str) {
        if (!check()) {
            String s = String.valueOf(expr.charAt(state.getPos()));
            state.setPos(state.getPos() + 1);
            return parseState(str + s);
        } else {
            StateImpl state;
            int i = 0;
            try {
                while (!str.equals(states.get(i).getData())) {
                    i++;
                }
                state = states.get(i);

            } catch (IndexOutOfBoundsException e) {
                state = new StateImpl(str, false);
                states.add(state);
            }
            return state;
        }

    }

    Tree parseNeg() {
        boolean inverted;
        if (expr.charAt(state.getPos()) == '!') {
            state.setPos(state.getPos() + 1);
            inverted = true;
        } else {
            inverted = false;
        }
        while (expr.charAt(state.getPos()) == '!') {
            state.setPos(state.getPos() + 1);
            inverted = !inverted;
        }
        if (expr.charAt(state.getPos()) == '(') {
            state.setPos(state.getPos() + 1);
            Tree res = impl();
            if (expr.charAt(state.getPos()) != ')') {
                System.out.println("???");
            }
            state.setPos(state.getPos() + 1);
            res.setInverted(inverted);
            return res;
        } else {
            if (!check()) {
                return new Tree(null, null, parseState(""), BinaryOperations.VAR, inverted);
            } else {
                System.out.println("???");
                return new Tree(null, null, new StateImpl("", false), BinaryOperations.VAR, inverted);
            }
        }
    }

    public Tree parse(String string) {
        this.expr = string + ";";
        state.setPos(0);
        return impl();
    }

    public void compute(Tree tree) {
        boolean result = tree.calc();
        boolean both = false;
        int trues = 0;
        int falses = 0;
        if (result) {
            trues++;
        } else {
            falses++;
        }
        for (int i = 1; i < Math.pow(2, states.size()); i++) {
            StringBuilder binary = new StringBuilder(Integer.toBinaryString(i));
            int diff = states.size() - binary.length();
            for (int j = 0; j < diff; j++) {
                binary.insert(0, "0");
            }
            for (int j = 0; j < states.size(); j++) {
                states.get(j).setVal(binary.charAt(j) != '0');
            }
            boolean results = tree.calc();
            if (results != result) {
                both = true;
            }
            if (results) {
                trues++;
            } else {
                falses++;
            }
        }
        if (both) {
            System.out.println("Satisfiable and invalid, " + trues + " true and " + falses + " false cases");
        } else if (result) {
            System.out.println("Valid");
        } else {
            System.out.println("Unsatisfiable");
        }
    }

    public boolean check() {
        return String.valueOf(expr.charAt(state.getPos())).equals("&") ||
                String.valueOf(expr.charAt(state.getPos())).equals("|") ||
                String.valueOf(expr.charAt(state.getPos())).equals("-") ||
                String.valueOf(expr.charAt(state.getPos())).equals("!") ||
                String.valueOf(expr.charAt(state.getPos())).equals(";") ||
                String.valueOf(expr.charAt(state.getPos())).equals("(") ||
                String.valueOf(expr.charAt(state.getPos())).equals(")");
    }
}
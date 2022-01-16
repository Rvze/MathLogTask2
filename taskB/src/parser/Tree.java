package parser;

import operations.Conjunction;
import operations.Disjunction;
import operations.Implication;

public class Tree {
    private final Tree left;
    private final Tree right;
    private final StateImpl state;
    private final BinaryOperations binaryOperations;
    private boolean inverted;

    public Tree(Tree left, Tree right, StateImpl state, BinaryOperations binaryOperations, boolean inverted) {
        this.left = left;
        this.right = right;
        this.state = state;
        this.binaryOperations = binaryOperations;
        this.inverted = inverted;
    }

    public String toString() {
        if (inverted) {
            switch (binaryOperations) {
                case CONJUNCTION:
                    Conjunction conjunction = new Conjunction(left, right);
                    return "(!" + conjunction.toPrefixString();
                case IMPLICATION:
                    Implication implication = new Implication(left, right);
                    return "(!" + implication.toPrefixString();
                case DISJUNCTION:
                    Disjunction disjunction = new Disjunction(left, right);
                    return "(!" + disjunction.toPrefixString();
                case VAR:
                    return "!(" + state.getData() + ")";
                default:
                    return "?";
            }
        } else {
            switch (binaryOperations) {
                case CONJUNCTION:
                    Conjunction conjunction = new Conjunction(left, right);
                    return conjunction.toPrefixString();
                case IMPLICATION:
                    Implication implication = new Implication(left, right);
                    return implication.toPrefixString();
                case DISJUNCTION:
                    Disjunction disjunction = new Disjunction(left, right);
                    disjunction.toPrefixString();
                case VAR:
                    return "!(" + state.getData() + ")";
                default:
                    return "??";
            }
        }
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public boolean calc() {
        if (binaryOperations == BinaryOperations.VAR) {
            if (inverted) {
                return !state.isVal();
            }
            return state.isVal();
        } else {
            switch (binaryOperations) {
                case DISJUNCTION:
                    if (inverted) {
                        return !(left.calc() || right.calc());
                    }
                    return left.calc() || right.calc();
                case CONJUNCTION:
                    if (inverted) {
                        return !(left.calc() && right.calc());
                    }
                    return left.calc() && right.calc();
                case IMPLICATION:
                    if (inverted) {
                        return !(!left.calc() || right.calc());
                    }
                    return !left.calc() || right.calc();
                default:
                    return false;
            }
        }
    }
}


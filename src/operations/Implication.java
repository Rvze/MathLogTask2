package operations;

import parser.Tree;

public class Implication extends BinaryLogicalExpression {

    public Implication(Tree left, Tree right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "->";
    }
}

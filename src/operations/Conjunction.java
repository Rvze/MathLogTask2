package operations;

import parser.Tree;

public class Conjunction extends BinaryLogicalExpression {

    public Conjunction(Tree left, Tree right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "&";
    }
}


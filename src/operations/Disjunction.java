package operations;

import parser.Tree;

public class Disjunction extends BinaryLogicalExpression {

    public Disjunction(Tree left, Tree right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "|";
    }
}

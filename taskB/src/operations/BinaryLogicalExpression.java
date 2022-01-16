package operations;

import parser.Tree;

import java.util.Objects;

public abstract class BinaryLogicalExpression implements Expression {
    private final Tree left;
    private final Tree right;

    public BinaryLogicalExpression(Tree left, Tree right) {
        this.left = left;
        this.right = right;
    }

    public abstract String getSymbol();

    @Override
    public String toString() {
        return "(" + left + getSymbol() + right + ")";
    }

    public String toPrefixString() {
        return "(" + getSymbol() + "," + left.toString() + "," + right.toString() + ")";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryLogicalExpression that = (BinaryLogicalExpression) o;
        return left.equals(that.left) && right.equals(that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    public Tree getLeft() {
        return left;
    }

    public Tree getRight() {
        return right;
    }
}

package operations;

import java.util.Objects;

public abstract class UnaryLogicalExpression implements Expression {
    private final Expression expression;

    public UnaryLogicalExpression(Expression expression) {
        this.expression = expression;
    }

    public abstract String getSymbol();

    @Override
    public String toString() {
        return getSymbol() + expression;
    }

    public String toPrefixString() {
        return "(" + getSymbol() + expression.toPrefixString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnaryLogicalExpression that = (UnaryLogicalExpression) o;
        return expression.equals(that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}

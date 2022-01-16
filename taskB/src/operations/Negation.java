package operations;


public class Negation extends UnaryLogicalExpression {

    public Negation(Expression expr) {
        super(expr);
    }

    @Override
    public String getSymbol() {
        return "!";
    }
}


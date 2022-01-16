package parser;

public class CommonParser {
    private final char end;
    private final State state;
    public char aChar = 0xffff;

    public CommonParser(char end, State state) {
        this.end = end;
        this.state = state;
    }

    public void nextChar() {
        aChar = state.hasNext() ? state.next() : end;
    }

    public Boolean test(char exp) {
        if (aChar == exp) {
            nextChar();
            return true;
        }
        return false;

    }

    protected void expect(final char c) throws Exception {
        if (aChar != c) {
            throw new Exception("Expected '" + c + "', found '" + aChar + "'");
        }
        nextChar();
    }

    protected void expect(final String value) throws Exception {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean eof() {
        return test(end);
    }


    protected boolean between(final char from, final char to) {
        return from <= aChar && aChar <= to;
    }

}

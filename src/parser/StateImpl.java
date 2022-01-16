package parser;

public class StateImpl implements State {
    private final String data;
    private int pos = 0;
    private boolean val;

    public StateImpl(String data, boolean val) {
        this.data = data;
        this.val = val;
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    public String getData() {
        return data;
    }

    public boolean isVal() {
        return val;
    }

    public void setVal(boolean val) {
        this.val = val;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}

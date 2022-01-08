package nio;

public class Calculator {
    private int index;

    public Calculator(int index) {
        this.index = index;
    }

    public int getNextIndex() {
        return ++index;
    }
}

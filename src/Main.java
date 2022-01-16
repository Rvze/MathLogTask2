import parser.LogicParser;
import parser.Tree;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        LogicParser parser = new LogicParser();
        Tree tree = parser.parse(string);
        parser.compute(tree);
    }
}

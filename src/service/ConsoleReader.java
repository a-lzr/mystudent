package service;

import java.util.Scanner;

public class ConsoleReader implements Reader {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public double ReadDouble() {
        return scanner.nextDouble();
    }

    @Override
    public int ReadInteger() {
        return scanner.nextInt();
    }

    @Override
    public String ReadString() {
        return scanner.next();
    }
}

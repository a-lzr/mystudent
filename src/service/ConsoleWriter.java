package service;

public class ConsoleWriter implements Writer {

    @Override
    public void WriteDouble(Double out) {
        System.out.println(out);
    }

    @Override
    public void WriteInteger(Integer out) {
        System.out.println(out);
    }

    @Override
    public void WriteString(String out) {
        System.out.println(out);
    }
}

package hw1.common;

public interface IOInterface {
    String read();
    String readFewLastLine(int i);
    void append(String msg);
    void flush();
}

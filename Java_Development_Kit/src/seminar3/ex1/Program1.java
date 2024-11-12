package seminar3;

import java.io.DataInput;
import java.io.InputStream;

public class Program <T extends Comparable<T>, V extends InputStream & DataInput, K extends Number>{
    private T t;
    private V v;
    private K k;

    public Program(T t, V v, K k) {
        this.t = t;
        this.v = v;
        this.k = k;
    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    public K getK() {
        return k;
    }
    public void showClass() {
        System.out.println(t.getClass().getName());
        System.out.println(k.getClass().getName());
        System.out.println(v.getClass().getName());
    }
}

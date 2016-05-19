package de.jpwinkler.daf.dafcore.util;

public class Counter {

    private int c = 0;

    public Counter() {
    }

    public Counter(final int initialValue) {
        c = initialValue;
    }

    public void inc() {
        c++;
    }

    public void add(final int i) {
        c += i;
    }

    public int get() {
        return c;
    }

    public void set(final int c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return String.valueOf(c);
    }
}

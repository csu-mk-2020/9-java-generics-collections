package com.example.task01;

import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

public class Pair<F1, F2> {
    private final F1 first;
    private final F2 second;

    private Pair(F1 first, F2 second) {
        this.first = first;
        this.second = second;
    }

    public static <F1, F2> Pair<F1, F2> of(F1 first, F2 second) {
        return new Pair<>(first, second);
    }

    public F1 getFirst() {
        return this.first;
    }

    public F2 getSecond() {
        return this.second;
    }

    public void ifPresent(BiConsumer<? super F1, ? super F2> action) {
        if (this.first != null && this.second != null) {
            action.accept(this.first, this.second);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        boolean firstEqual;
        if (this.first != null && ((Pair<F1, F2>) obj).first != null) {
            firstEqual = this.first.equals(((Pair<F1, F2>) obj).first);
        } else firstEqual = this.first == null && ((Pair<F1, F2>) obj).first == null;
        boolean secondEqual;
        if (this.second != null && ((Pair<F1, F2>) obj).second != null) {
            secondEqual = this.second.equals(((Pair<F1, F2>) obj).second);
        } else secondEqual = this.second == null && ((Pair<F1, F2>) obj).second == null;
        return firstEqual && secondEqual;
    }

    @Override
    public int hashCode() {
        int HashCode1 = 0;
        if (this.first != null) {
            HashCode1 = this.first.hashCode();
        }
        int HashCode2 = 0;
        if (this.second != null) {
            HashCode2 = this.second.hashCode();
        }
        return HashCode1 ^ HashCode2;
    }
}
package com.example.task01;

import java.util.function.BiConsumer;

/*
public class Pair {
    // TODO напишите реализацию
    */
public class Pair<A, B> {
    private final A first;
    private final B second;

    private Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<>(first, second);
    }

    public A getFirst() {
        return this.first;
    }

    public B getSecond() {
        return this.second;
    }

    public void ifPresent(BiConsumer<? super A, ? super B> action) {
        if (this.first != null && this.second != null) {
            action.accept(this.first, this.second);
        }
    }

    @Override
    public int hashCode() {
        int firstHashCode = 0;
        if (this.first != null) {
            firstHashCode = this.first.hashCode();
        }
        int secondHashCode = 0;
        if (this.second != null) {
            secondHashCode = this.second.hashCode();
        }
        return firstHashCode ^ secondHashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        boolean firstEqual;
        if (this.first != null && ((Pair<A, B>) obj).first != null) {
            firstEqual = this.first.equals(((Pair<A, B>) obj).first);
        } else firstEqual = this.first == null && ((Pair<A, B>) obj).first == null;
        boolean secondEqual;
        if (this.second != null && ((Pair<A, B>) obj).second != null) {
            secondEqual = this.second.equals(((Pair<A, B>) obj).second);
        } else secondEqual = this.second == null && ((Pair<A, B>) obj).second == null;
        return firstEqual && secondEqual;
    }

}

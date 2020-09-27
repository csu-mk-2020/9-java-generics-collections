package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<T, U> {
    private final T first;
    private final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return this.first;
    }

    public U getSecond() {
        return this.second;
    }

    public void ifPresent(BiConsumer<? super T, ? super U> action) {
        if (this.first != null && this.second != null) {
            action.accept(this.first, this.second);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        return this.first.equals(((Pair<T, U>) obj).first) && this.second.equals(((Pair<T, U>) obj).second);
    }

    @Override
    public int hashCode() {
        return this.first.hashCode() ^ this.second.hashCode();
    }
}

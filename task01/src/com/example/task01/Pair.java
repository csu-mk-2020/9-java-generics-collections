package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T1, T2> {
    private final T1 first;
    private final T2 second;

    private Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return this.first;
    }

    public T2 getSecond() {
        return this.second;
    }

    public boolean equals(Object rhs) {
        if (this == rhs) {
            return true;
        }

        if (rhs == null
                || this.getClass() != rhs.getClass()
                || (this.first == null && ((Pair<T1, T2>) rhs).first != null)
                || (this.first != null && ((Pair<T1, T2>) rhs).first == null)
                || (this.second == null && ((Pair<T1, T2>) rhs).second != null)
                || (this.second != null && ((Pair<T1, T2>) rhs).second == null)
                || !Objects.equals(this.first, ((Pair<T1, T2>) rhs).first)
                || !Objects.equals(this.second, ((Pair<T1, T2>) rhs).second)
        ){
            return false;
        }

        return true;
    }

    public int hashCode() {
        return Objects.hash(this.first, this.second);
    }

    public static <T, T2> Pair<T, T2> of(T first, T2 second) {
        return new Pair<>(first, second);
    }

    public void ifPresent(BiConsumer<? super T1, ? super T2> consumer) {
        if (this.first != null && this.second != null)
            consumer.accept(this.first, this.second);
    }
}

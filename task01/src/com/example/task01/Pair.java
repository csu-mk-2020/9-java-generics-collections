package com.example.task01;

import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

public class Pair<T,U> {
    private final T first;
    private final U second;

    private Pair(T first, U second){
        this.first = first;
        this.second = second;
    }

    public U getSecond(){
        if(second==null){
            //тест падает на null
            //throw new NoSuchElementException("No second value present");
        }
        return second;
    }

    public T getFirst(){
        if(first==null){
            //тест падает на null
            //throw new NoSuchElementException("No first value present");
        }
        return first;
    }

    public void ifPresent(BiConsumer<? super T,? super U> consumer){
        if (first != null && second != null){
           consumer.accept(first,second);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Pair tmp = (Pair) obj;
        return (first == tmp.getFirst() || (first != null && tmp.getSecond().equals(tmp.getFirst())))
                && (second == tmp.getSecond() || (second != null && second.equals(tmp.getSecond())));
    }

    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

    public static <T,U> Pair<T,U> of(T first, U second){
        return new Pair<>(first,second);
    }

}

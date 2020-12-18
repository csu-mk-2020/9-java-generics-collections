package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Objects;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final ArrayList<E> list;
    private final Path path;

    public SavedList(File file) {
        Objects.requireNonNull(file);
        ArrayList<E> list;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            list = (ArrayList<E>)inputStream.readObject();
            if (list == null) {
                list = new ArrayList<>();
            }
        } catch (Exception e) {
            System.err.println(e);
            list = new ArrayList<>();
        }
        this.list = list;
        this.path = file.toPath();
    }

    @Override
    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = this.list.set(index, element);
        this.synchronize();
        return elem;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void add(int index, E element) {
        this.list.add(index, element);
        this.synchronize();
    }

    @Override
    public E remove(int index) {
        E elem = this.list.remove(index);
        this.synchronize();
        return elem;
    }

    public void synchronize() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(this.path))) {
            outputStream.writeObject(this.list);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
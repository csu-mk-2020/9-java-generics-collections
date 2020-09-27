package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final ArrayList<E> list;
    private final Path path;

    public SavedList(File file) {
        ArrayList<E> tmp;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            tmp = (ArrayList<E>) objectInputStream.readObject();
        } catch (Exception ignored) {
            tmp = new ArrayList<>();
        }
        this.list = tmp;
        this.path = file.toPath();

    }

    private void sync(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(this.path))) {
            outputStream.writeObject(this.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = this.list.set(index, element);
        this.sync();
        return elem;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void add(int index, E element) {
        this.list.add(index, element);
        this.sync();
    }

    @Override
    public E remove(int index) {
        E elem = this.list.remove(index);
        this.sync();
        return elem;
    }
}

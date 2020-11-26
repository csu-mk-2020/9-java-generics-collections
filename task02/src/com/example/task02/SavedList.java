package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Objects;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private ArrayList<E> list;
    final private File file;

    public SavedList(File file) {
        Objects.requireNonNull(file);
        this.file = file;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            list = (ArrayList<E>) inputStream.readObject();
            if (list == null) {
                list = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            list = new ArrayList<>();
        }
    }
    private void loadList(){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(list);
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E st = list.set(index,element);
        loadList();
        return st;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index,element);
        loadList();
    }

    @Override
    public E remove(int index) {
        E rmv = list.remove(index);
        loadList();
        return rmv;
    }
}

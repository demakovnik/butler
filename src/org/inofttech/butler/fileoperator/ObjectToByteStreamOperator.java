package org.inofttech.butler.fileoperator;



import org.inofttech.butler.exception.StorageException;

import java.io.*;

public class ObjectToByteStreamOperator<T> implements FileStorageStrategy<T> {
    @Override
    public T doRead(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (T) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }

    @Override
    public void doWrite(T item, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(item);
        }
    }
}


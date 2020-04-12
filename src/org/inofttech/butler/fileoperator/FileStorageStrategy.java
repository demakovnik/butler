package org.inofttech.butler.fileoperator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileStorageStrategy<T> {

    T doRead(InputStream is) throws IOException;

    void doWrite(T resume, OutputStream os) throws IOException;
}


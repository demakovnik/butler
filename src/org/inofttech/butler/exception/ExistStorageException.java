package org.inofttech.butler.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("Объект с uuid " + uuid + " уже есть в хранилище.", uuid);
    }

    public ExistStorageException(Exception e) {
        super(e.getMessage(), e);
    }
}

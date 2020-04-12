package org.inofttech.butler.storage;

import org.inofttech.butler.exception.ExistStorageException;
import org.inofttech.butler.exception.NotExistStorageException;
import org.inofttech.butler.exception.StorageException;
import org.inofttech.butler.model.Item;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class AbstractStorage<K, T extends Item> implements Storage<T> {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    protected final Comparator<T> COMPARATOR = Comparator.comparing(T::getName).
            thenComparing(T::getUuid);

    @Override
    public void update(T item) {
        LOG.info("Update " + item);
        updateByPointer(getPointerIfExistElement(item.getUuid()), item);
    }

    @Override
    public void save(T item) {
        LOG.info("Save " + item);
        insertIntoStorage(item, getPointerIfNotExistElement(item.getUuid()));
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        deleteElementByPointer(getPointerIfExistElement(uuid));
    }

    @Override
    public T get(String uuid) {
        LOG.info("Get " + uuid);
        return getItemByPointer(getPointerIfExistElement(uuid));
    }

    private K getPointerIfExistElement(String uuid) {
        K pointer = getPointerToItem(uuid);
        if (isExistPointer(pointer)) {
            return pointer;
        }
        LOG.warning("Resume " + uuid + " not exist");
        throw new NotExistStorageException(uuid);
    }

    private K getPointerIfNotExistElement(String uuid) {
        K pointer = getPointerToItem(uuid);
        if (isExistPointer(pointer)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return pointer;
    }

    @Override
    public List<T> getAllSorted() {
        LOG.info("GetAllSorted");
        try {

            return getList().stream().sorted(COMPARATOR).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("IO Error", null, e);
        }
    }

    protected abstract List<T> getList() throws IOException;

    protected abstract T getItemByPointer(K pointer);

    protected abstract void deleteElementByPointer(K pointer);

    protected abstract boolean isExistPointer(K pointer);

    protected abstract K getPointerToItem(String uuid);

    protected abstract void insertIntoStorage(T resume, K pointer);

    protected abstract void updateByPointer(K pointer, T resume);
}

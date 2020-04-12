package org.inofttech.butler.storage;



import org.inofttech.butler.exception.StorageException;
import org.inofttech.butler.fileoperator.FileStorageStrategy;
import org.inofttech.butler.model.Item;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PathStorage<T extends Item> extends AbstractStorage<Path, T> {

    private Path directory;
    private FileStorageStrategy strategy;

    public PathStorage(File dir, FileStorageStrategy strategy) {
        Objects.requireNonNull(strategy, "strategy must not be null");
        this.strategy = strategy;
        Path localpath = Paths.get(dir.toURI());
        if (!Files.isDirectory(localpath) || !Files.isWritable(localpath) || !Files.isReadable(localpath)) {
            throw new IllegalArgumentException(dir +
                    " is not directory or is not readable/writable");
        }
        directory = localpath;
    }

    @Override
    protected T getItemByPointer(Path pointer) {

        try {
            return (T) strategy.doRead(new BufferedInputStream((Files.newInputStream(pointer))));
        } catch (IOException e) {
            throw new StorageException("Path read error", pointer.getFileName().toString(), e);
        }
    }

    @Override
    protected void deleteElementByPointer(Path pointer) {
        try {
            Files.delete(pointer);
        } catch (IOException e) {
            throw new StorageException("Error while deleting", pointer.getFileName().toString(), e);
        }
    }

    @Override
    protected boolean isExistPointer(Path pointer) {
        return Files.isRegularFile(pointer);
    }

    @Override
    protected Path getPointerToItem(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void insertIntoStorage(T item, Path pointer) {
        try {
            Files.createFile(pointer);
        } catch (IOException e) {
            throw new StorageException("Can't create file", pointer.getFileName().toString(), e);
        }
        updateByPointer(pointer, item);
    }


    @Override
    protected void updateByPointer(Path pointer, T item) {
        try {
            strategy.doWrite(item, new BufferedOutputStream(Files.newOutputStream(pointer)));
        } catch (IOException e) {
            throw new StorageException("Can't write Path", pointer.getFileName().toString());
        }
    }

    @Override
    public void clear() {
        getStreamItems().forEach(this::deleteElementByPointer);
    }

    @Override
    public int size() {
        return (int) getStreamItems().count();
    }

    @Override
    protected List<T> getList() {
        return getStreamItems().map(this::getItemByPointer).collect(Collectors.toList());
    }

    private Stream<Path> getStreamItems() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("IO Error", null, e);
        }
    }
}

package com.aarya.bms.bms.merging.persistance;

import java.io.IOException;
import java.util.Optional;

public interface IPersistence<T> {

    T save(T entity) throws IOException;

    Optional<T> findById(String id);

    void deleteById(String id) throws IOException;
}
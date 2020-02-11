package mate.academy.internetshop.service;

import java.util.List;

import mate.academy.internetshop.exceptions.DataProcessingException;

public interface GenericService<T, N> {

    T create(T entity) throws DataProcessingException;

    T get(Long entityId) throws DataProcessingException;

    T update(T entity) throws DataProcessingException;

    boolean delete(Long entityId) throws DataProcessingException;

    List<T> getAll() throws DataProcessingException;
}

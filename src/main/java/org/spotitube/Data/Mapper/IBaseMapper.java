package org.spotitube.Data.Mapper;

import java.util.Optional;

public interface IBaseMapper<T> {
    Optional<T> find(int id);
    void insert(T t);
    void update(T t);
    void delete(int id);
}

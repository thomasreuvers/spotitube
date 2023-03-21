package org.spotitube.Data.Mapper;

import java.util.Optional;

public interface IBaseMapper {
    <T> Optional<T> find(int id);
    <T> void insert(T t);
    <T> void update(T t);
    <T> void delete(T t);
}

package org.spotitube.Data.Mapper;

import java.util.List;
import java.util.Optional;

public interface IBaseMapper<T> {
    Optional<T> find(int id);
    void save(String query, List<Object> queryParams);

    List<T> all(String query, List<Object> queryParams);
}

package org.spotitube.Data.Mapper;

import java.util.List;
import java.util.Optional;

public interface IBaseMapper<T> {

    Optional<T> find(String query, List<Object> queryParams);
    void save(String query, List<Object> queryParams);

    List<T> all(String query, List<Object> queryParams);

}

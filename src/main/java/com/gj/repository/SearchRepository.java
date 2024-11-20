package com.gj.repository;

import com.gj.pojo.Search;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends CrudRepository<Search, Integer> {
    Search getByUserId(Integer userId);
}

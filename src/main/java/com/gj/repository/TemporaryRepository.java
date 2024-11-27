package com.gj.repository;

import com.gj.pojo.Temporary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryRepository extends CrudRepository<Temporary, Integer> {
}

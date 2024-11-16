package com.gj.repository;

import com.gj.pojo.Tabulate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabulateRepository extends CrudRepository<Tabulate, Integer> {
    List<Tabulate> findByTabulateType (Integer orderType);
}



package com.gj.repository;

import com.gj.pojo.Tabulate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabulateRepository extends CrudRepository<Tabulate, Integer> {
    List<Tabulate> findByTabulateType(Integer tabulateType);

    @Query(value = "select * from tb_tabulate where tabulate_name like %?#{#tabulateName}%", nativeQuery = true)
    List<Tabulate> findByTabulateName(String tabulateName);

    List<Tabulate> getByShopId(Integer shopId);
}



package com.gj.repository;

import com.gj.pojo.Tabulate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabulateRepository extends JpaRepository<Tabulate, Integer> {
    List<Tabulate> findByTabulateType (Integer tabulateType);

    List<Tabulate> findByTabulateName(String tabulateName);
}



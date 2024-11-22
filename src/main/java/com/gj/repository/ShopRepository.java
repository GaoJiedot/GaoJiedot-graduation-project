package com.gj.repository;

import com.gj.pojo.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Integer> {
    List<Shop> findByShopPhone(String shopPhone);
}

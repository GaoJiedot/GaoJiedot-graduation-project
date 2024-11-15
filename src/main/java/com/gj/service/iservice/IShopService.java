package com.gj.service.iservice;

import com.gj.pojo.Shop;
import com.gj.pojo.dto.ShopDto;

public interface IShopService {
    Shop add(ShopDto shop);

    Shop get(Integer shopId);

    Shop update(ShopDto shop);

    void delete(Integer shopId);
}

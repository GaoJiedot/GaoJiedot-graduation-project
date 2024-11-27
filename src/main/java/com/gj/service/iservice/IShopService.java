package com.gj.service.iservice;

import com.gj.pojo.Shop;
import com.gj.pojo.dto.ShopDto;

import java.util.List;

public interface IShopService {
    Shop add(ShopDto shop);

    Shop get(Integer shopId);

    Shop update(ShopDto shop);

    void delete(Integer shopId);
    void updateShopStatus(Integer shopId, Integer shopStatus);

    List<Shop> getAll();


    List<Shop> getShopByPhone(String shopPhone);

    Shop uploadShopLogo(Integer shopId, String avatarPath);

    Shop uploadShopImages(Integer shopId, String avatarPath);

    Shop updateRating(Integer shopId, ShopDto shop);
}

package com.gj.service;

import com.gj.pojo.Shop;
import com.gj.pojo.dto.ShopDto;
import com.gj.repository.ShopRepository;
import com.gj.service.iservice.IShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService implements IShopService {
    @Autowired
    ShopRepository shopRepository;


    @Override
    public Shop add(ShopDto shop) {
        Shop shopPojo = new Shop();
        BeanUtils.copyProperties(shop, shopPojo);
        return shopRepository.save(shopPojo);
    }

    @Override
    public Shop get(Integer shopId) {
        return shopRepository.findById(shopId).orElseThrow( ()-> new IllegalArgumentException("店铺不存在"));
    }

    @Override
    public Shop update(ShopDto shop) {
        Shop shopPojo = new Shop();
        BeanUtils.copyProperties(shop, shopPojo);
        return shopRepository.save(shopPojo);
    }

    @Override
    public void delete(Integer shopId) {
        shopRepository.deleteById(shopId);
    }
}

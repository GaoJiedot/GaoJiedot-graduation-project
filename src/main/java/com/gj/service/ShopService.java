package com.gj.service;

import com.gj.pojo.Shop;
import com.gj.pojo.dto.ShopDto;
import com.gj.repository.ShopRepository;
import com.gj.service.iservice.IShopService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void updateShopStatus(Integer shopId, Integer shopStatus) {
        Shop shop= shopRepository.findById(shopId).orElseThrow( ()-> new IllegalArgumentException("店铺不存在"));
        shop.setShopStatus(shopStatus);
        shopRepository.save(shop);
    }

    @Override
    public List<Shop> getAll() {
        return (List<Shop>) shopRepository.findAll();
    }

    @Override
    public List<Shop> getShopByPhone(String shopPhone) {
        return shopRepository.findByShopPhone(shopPhone);
    }

    @Override
    public Shop uploadShopLogo(Integer shopId, String avatarPath) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new IllegalArgumentException("用户未找到"));
        shop.setShopLogo(avatarPath);
        return shopRepository.save(shop);
    }

    @Override
    public Shop uploadShopImages(Integer shopId, String avatarPath) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(() -> new IllegalArgumentException("用户未找到"));
        shop.setShopLogo(avatarPath);
        return shopRepository.save(shop);
    }

}

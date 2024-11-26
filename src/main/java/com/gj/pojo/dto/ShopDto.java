package com.gj.pojo.dto;

import lombok.Data;

@Data
public class ShopDto {

    private Integer shopId;
    private String shopName;
    private String shopAddress;
    private  float shopRating;
    private String shopImages;
    private String shopLogo;
    private String shopPhone;
    private  String shopKeeper;
    private  String shopBusinessHours;

}

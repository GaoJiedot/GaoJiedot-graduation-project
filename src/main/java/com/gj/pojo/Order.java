package com.gj.pojo;

import jakarta.persistence.*;
import lombok.Data;
@Table(name = "tb_order")
@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "order_name")
    private String orderName;
    @Column(name = "order_price")
    private Integer orderPrice;
    @Column(name = "order_status")
    private Integer orderStatus;
    @Column(name = "order_tabs")
    private String orderTabs;
    @Column(name = "shop_id")
    private Integer shopId;
    @JoinColumn(name = "user_phone")
    private String userPhone;
    @Column(name = "order_image")
    private String orderImage;



}

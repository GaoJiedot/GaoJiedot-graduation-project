package com.gj.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "tb_temporary")
@Entity
@Data
public class Temporary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shop_id")
    private Integer shopId;
    @Column(name="shop_name")
    private String shopName;
    @Column(name="shop_phone")
    private String shopPhone;
    @Column(name="shop_keeper")
    private String shopKeeper;
    @Column(name="user_id")
    private Integer userId;

}

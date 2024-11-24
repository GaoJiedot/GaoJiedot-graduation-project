package com.gj.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "tb_tabulate")
@Entity
@Data
public class Tabulate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tabulate_id")
    private Integer tabulateId;
    @Column(name = "tabulate_name")
    private String tabulateName;
    @Column(name = "tabulate_tabs")
    private String tabulateTabs;
    @Column(name = "tabulate_type" ,columnDefinition = "int default 1")
    private Integer tabulateType =1;
    @Column(name = "tabulate_image")
    private String tabulateImage;
    @JoinColumn(name = "shop_id")
    private Integer shopId;
    @Column(name = "tabulate_price")
    private Integer tabulatePrice;
}

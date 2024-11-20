package com.gj.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "tb_tabulate")
@Entity
@Data
public class Tabulate {
    @Id
    @Column(name = "tabulate_id")
    private Integer tabulateId;
    @Column(name = "tabulate_name")
    private String tabulateName;
    @Column(name = "tabulate_tabs")
    private String tabulateTabs;
    @Column(name = "tabulate_type")
    private Integer tabulateType;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shopId;

}

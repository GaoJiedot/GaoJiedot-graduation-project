package com.gj.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

}

package com.gj.pojo.dto;

import com.gj.pojo.Shop;
import lombok.Data;

@Data
public class TabulateDto {
    private Integer tabulateId;
    private String tabulateName;
    private String tabulateTabs;
    private Integer tabulateType;
    private Shop shopId;
}

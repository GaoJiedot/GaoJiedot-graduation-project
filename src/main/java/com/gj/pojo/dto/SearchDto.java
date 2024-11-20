package com.gj.pojo.dto;

import lombok.Data;

@Data
public class SearchDto {
    private Integer searchId;
    private String searchHistory;
    private String searchHot;
    private Integer userId;
}

package com.gj.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "tb_search")
@Entity
@Data
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_id")
    private Integer searchId;
    @Column(name = "search_history")
    private String searchHistory;
    @Column(name = "search_hot")
    private String searchHot;
    @Column(name = "user_id")
    private Integer userId;

}

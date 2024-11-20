package com.gj.controller;

import com.gj.pojo.Search;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    ISearchService searchService;
    @GetMapping("/history/{userId}")
    public ResponseMessage searchHistory(@PathVariable Integer userId){
        Search search = new Search();
        search.setUserId(userId);
        Search result=searchService.searchHistory(search);
         return ResponseMessage.success(result);

    }
}

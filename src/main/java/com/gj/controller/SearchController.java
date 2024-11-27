package com.gj.controller;

import com.gj.pojo.Search;
import com.gj.pojo.User;
import com.gj.pojo.responseMessage.ResponseMessage;
import com.gj.service.iservice.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    ISearchService searchService;

    @GetMapping("/history/{userId}")
    public ResponseMessage searchHistory(@PathVariable Integer userId) {
        User user = new User();
        user.setUserId(userId);
        Search result = searchService.searchHistory(userId);
        return ResponseMessage.success(result);

    }

    @PostMapping("add/SearchHot")
    public ResponseMessage addSearchHot(@RequestBody SearchDto search) {
        Search searchNew=searchService.addSearchHot(search);
        return ResponseMessage.success(searchNew);
    }

}

package com.gj.controller;

import com.gj.pojo.Search;
import com.gj.pojo.User;
import com.gj.pojo.dto.SearchDto;
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

    @PostMapping("add/SearchHistory")
    public ResponseMessage addSearchHot(@RequestBody SearchDto search) {
        Search searchNew = searchService.addSearchHot(search);
        return ResponseMessage.success(searchNew);
    }

    @DeleteMapping("/delete/{searchId}")
    public ResponseMessage deleteSearchHot(@PathVariable Integer searchId) {
        searchService.deleteSearchHot(searchId);
        return ResponseMessage.success("删除成功");
    }
}

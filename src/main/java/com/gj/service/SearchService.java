package com.gj.service;

import com.gj.pojo.Search;
import com.gj.repository.SearchRepository;
import com.gj.service.iservice.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService implements ISearchService {
    @Autowired
    SearchRepository searchRepository;

    @Override
    public Search searchHistory(Search search) {
        return searchRepository.getByUserId(search.getUserId());
    }
}

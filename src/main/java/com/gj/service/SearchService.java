package com.gj.service;

import com.gj.pojo.Search;
import com.gj.pojo.dto.SearchDto;
import com.gj.repository.SearchRepository;
import com.gj.service.iservice.ISearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService implements ISearchService {
    @Autowired
    SearchRepository searchRepository;


    @Override
    public Search addSearchHot(SearchDto search) {
       Search searchPojo = new Search();
        BeanUtils.copyProperties(search,searchPojo);
        return searchRepository.save(searchPojo);
    }

    @Override
    public Search searchHistory(Integer userId) {
        return searchRepository.getByUserId(userId);
    }

    @Override
    public void deleteSearchHot(Integer searchId) {
        searchRepository.deleteById(searchId);
    }


}

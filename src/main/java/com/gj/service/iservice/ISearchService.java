package com.gj.service.iservice;

import com.gj.pojo.Search;
import com.gj.pojo.dto.SearchDto;

public interface ISearchService {


    Search addSearchHot(SearchDto search);

    Search searchHistory(Integer userId);

    void deleteSearchHot(Integer searchId);
}

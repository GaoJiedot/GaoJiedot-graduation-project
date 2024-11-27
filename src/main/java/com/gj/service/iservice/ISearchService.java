package com.gj.service.iservice;

import com.gj.pojo.Search;
import com.gj.pojo.dto.SearchDto;

public interface ISearchService {
    Search searchHistory(Search search);

    Search addSearchHot(SearchDto search);
}

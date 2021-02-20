package com.pg.newsOrdering;

import com.pg.news.SearchNewsResponse;

public interface NewsSearchService {

  public SearchNewsResponse search(String searchString, int pageNumber, int pageSize);
}

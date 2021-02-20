package com.pg.newsOrdering;

import com.pg.commons.utils.ListUtil;
import com.pg.index.Index;
import com.pg.index.IndexService;
import com.pg.news.NewsRepository;
import com.pg.news.SearchNewsResponse;
import com.pg.commons.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class NewsSearchServiceImpl implements NewsSearchService {
  @Autowired
  IndexService indexService;
  @Autowired
  NewsRepository newsRepository;

  @Override
  public SearchNewsResponse search(String searchString, int pageNumber, int pageSize) {
    final List<String> keywords = StringUtil.getWords(searchString);
    final List<Index> indices = indexService.searchNews(keywords);
    Map<String, Long> docCount = new HashMap<>();
    indices.forEach(index -> {
      docCount.put(index.getNewsId(), docCount.getOrDefault(index.getNewsId(), 0L)+index.getCount());
    });
    final List<String> newsIds = docCount.entrySet().stream().sorted(Comparator.comparingLong(Entry::getValue)).map(Entry::getKey).collect(Collectors.toList());
    if (searchString.isEmpty()) {
//      newsRepository.
    }
    return new SearchNewsResponse(newsIds.size(), newsRepository.findByIdIn(ListUtil.getPage(newsIds, pageNumber, pageSize)));
  }
}

package com.pg.news;

import com.pg.newsOrdering.NewsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {

  @Autowired NewsService newsService;
  @Autowired
  NewsSearchService newsSearchService;

  @PostMapping("/news")
  public News addNews(@RequestBody AddNewsRequest addNewsRequest) {
    return newsService.addNews(addNewsRequest);
  }

  @GetMapping("/news/search")
  public SearchNewsResponse search(@RequestParam(value = "query") String searchString,
                                   @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
    return newsSearchService.search(searchString, pageNumber, pageSize);
  }
}

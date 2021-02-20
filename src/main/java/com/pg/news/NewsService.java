package com.pg.news;

import com.pg.index.IndexService;
import com.pg.newsProcessor.NewsProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

  @Autowired
  private NewsRepository newsRepository;

  @Autowired
  IndexService indexService;

  @Autowired
  NewsProcessor newsProcessor;

  public News addNews(AddNewsRequest addNewsRequest) {
    News news = newsRepository.save(News.fromRequest(addNewsRequest));
    newsProcessor.processNews(news);
    return news;
  }



}

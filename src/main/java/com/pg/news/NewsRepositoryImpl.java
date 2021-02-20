package com.pg.news;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

@Service
public class NewsRepositoryImpl implements NewsRepository{

  Map<String, News> map = new ConcurrentHashMap<>();

  @Override
  public List<News> findByIdIn(Collection<String> list) {
    return list
        .stream()
        .filter(id -> map.containsKey(id))
        .map(id -> map.get(id))
        .collect(toList());
  }

  @Override
  public News save(News news) {
    if (!map.containsKey(news.getId())) {
      map.put(news.getId(), news);
    } else {
      throw new RuntimeException("Error saving news record");
    }
    return news;
  }
}

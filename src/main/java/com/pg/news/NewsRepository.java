package com.pg.news;

import java.util.Collection;
import java.util.List;

public interface NewsRepository  {
  public List<News> findByIdIn(Collection<String> list);

  News save(News fromRequest);
}

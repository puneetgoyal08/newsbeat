package com.pg.news;

import com.pg.news.AddNewsRequest;

public class NewsHelper {
  public static AddNewsRequest getAddNewsRequest(String content) {
    return new AddNewsRequest("title", content, "url", "coverUrl");
  }
}

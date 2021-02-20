package com.pg.news;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddNewsRequest {
  private String title;
  private String content;
  private String thumbnailUrl;
  private String coverUrl;
}

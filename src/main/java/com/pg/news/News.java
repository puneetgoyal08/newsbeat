package com.pg.news;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class News implements Serializable {

  static Random random = new Random();
  private String id;
  private String title;
  private String content;
  private String thumbnailUrl;
  private String coverUrl;

  public static News fromRequest(AddNewsRequest addNewsRequest) {
    return News.builder()
        .id(UUID.randomUUID().toString())
        .content(addNewsRequest.getContent())
        .coverUrl(addNewsRequest.getCoverUrl())
        .thumbnailUrl(addNewsRequest.getThumbnailUrl())
        .title(addNewsRequest.getTitle())
        .build();

  }
}

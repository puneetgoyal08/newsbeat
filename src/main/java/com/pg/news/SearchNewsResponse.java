package com.pg.news;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SearchNewsResponse {
  private Integer totalCount;
  private List<News> news;
}

package com.pg.newsOrdering;

import java.util.List;

public interface FilterWordService {
  /**
   * Filters unimportant words so that search results are not impacted by them.
   * @param wordList word list to be filtered
   * @return filtered list
   */
  List<String> filterWords (List<String> wordList);
}

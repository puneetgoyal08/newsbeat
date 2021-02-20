package com.pg.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IndexService {

  @Autowired IndexRepository indexRepository;

  public void addIndex(String id, Map<String, Integer> countMap) {
    List<Index> indexList = new ArrayList<>();
    countMap.forEach((word, count) -> {
      indexList.add(Index.builder().count(count.longValue()).word(word).newsId(id).build());
    });
    indexRepository.saveAll(indexList);
  }

  public List<Index> searchNews(List<String> wordList) {
    return indexRepository.findByWordIn(wordList);
  }
}

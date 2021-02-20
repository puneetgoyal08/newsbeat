package com.pg.index;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.emptyMap;

@Service
public class IndexRepositoryImpl implements IndexRepository {

  Map<String, Map<String, Long>> wordIndexMap = new ConcurrentHashMap<>();

  @Override
  public List<Index> findByWordIn(List<String> wordList) {
    List<Index> indexList = new ArrayList<>();
    wordList.forEach(word -> {
      final Map<String, Long> indexCountMap = wordIndexMap.getOrDefault(word, emptyMap());
      indexCountMap.forEach((newsId, count) -> {
        indexList.add(new Index(word, newsId, count));
      });
    });
    return indexList;
  }

  @Override
  public void save(Index index) {
    final Map<String, Long> indexCountMapForAWord = wordIndexMap.getOrDefault(index.getWord(), new ConcurrentHashMap<>());
    indexCountMapForAWord.put(index.getNewsId(), indexCountMapForAWord.getOrDefault(index.getNewsId(), 0L) + 1L);
    wordIndexMap.put(index.getWord(), indexCountMapForAWord);
  }

  @Override
  public void saveAll(List<Index> indexList) {
    indexList.forEach(this::save);
  }
}

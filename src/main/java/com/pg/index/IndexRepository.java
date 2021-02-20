package com.pg.index;

import java.util.List;

/**
 * IndexRepository maintains inverted index for every word in a news document.
 * For every word it maintains how many document it was found in and with what frequency.
 */
public interface IndexRepository {
  List<Index> findByWordIn(List<String> word);

  void save(Index index);

  void saveAll(List<Index> indexList);
}

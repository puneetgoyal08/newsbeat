package com.pg.newsProcessor;

import com.pg.index.IndexService;
import com.pg.news.News;
import com.pg.newsOrdering.FilterWordService;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static com.pg.commons.utils.StringUtil.getWords;
import static java.util.concurrent.CompletableFuture.runAsync;

/**
 * NewsProcessor maintains a queue. Whenever a new request comes in to process a news, it adds it into the queue.
 * As of now the queue is only triggered when a new request comes.
 */
@Service
public class NewsProcessorImpl implements NewsProcessor {

  @Autowired
  private IndexService indexService;

  @Autowired
  private FilterWordService filterWordService;

  Queue<News> queue = new LinkedList<>();

  @Override
  public void processNews(News news) {
    queue.offer(news);
    runAsync(this::processQueue);
  }

  @Override
  public int pendingCount() {
    return queue.size();
  }

  @Override
  public void triggerProcessing() {
    processQueue();
  }

  @Synchronized
  public void processQueue() {
    while (!queue.isEmpty()) {
      final News news = queue.poll();
      Map<String, Integer> countMap = new HashMap<>();
      List<String> wordList = getWords(news.getContent());
      List<String> filteredWords = filterWordService.filterWords(wordList);
      for (String word : filteredWords) {
        countMap.put(word, countMap.getOrDefault(word, 0) + 1);
      }
      indexService.addIndex(news.getId(), countMap);
    }
  }



}

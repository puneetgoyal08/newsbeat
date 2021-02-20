package com.pg.newsProcessor;

import com.pg.news.News;

/**
 * NewsProcessor takes care of processing every news object asynchronously
 * While processing it creates the index for every word present in the news.
 */
public interface NewsProcessor {

  public void processNews(News news);

  /**
   * pending count of number of news yet to be processed
   * @return pending count
   */
  public int pendingCount();

  /**
   * trigger processing of the queue.
   * This can help any client trigger on the frequency on the basis of there requirement
   */
  public void triggerProcessing();
}

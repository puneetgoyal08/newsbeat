package com.pg.news;

import com.google.gson.Gson;
import com.pg.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class NewsControllerTest extends BaseTest {

  private static Gson gson = new Gson();

  @Test
  void addNews_validParams_success() throws Exception {
    AddNewsRequest request = NewsHelper.getAddNewsRequest("content1");
    new TestRunner()
        .addNewsAndVerify(request);
  }

  @Test
  void search_validParams_success() throws Exception {
    AddNewsRequest request = NewsHelper.getAddNewsRequest("content2");
    new TestRunner()
        .addNewsAndVerify(request)
        .searchNewsAndAssert("content2", 1, 1, News.fromRequest(request));
  }

  @Test
  void search_validParams_emptySearchResults() throws Exception {
    AddNewsRequest request = NewsHelper.getAddNewsRequest("content");
    new TestRunner()
        .addNewsAndVerify(request)
        .searchNewsAndAssert("content2", 1, 1, 0);
  }

  private class TestRunner {
    public TestRunner addNewsAndVerify(AddNewsRequest request) throws Exception {
      News response = gson.fromJson(mvc.perform(post("/news")
          .contentType(MediaType.APPLICATION_JSON)
          .content(gson.toJson(request)))
          .andReturn().getResponse().getContentAsString(), News.class);
      assertNotNull(response);
      assertEquals(request.getContent(), response.getContent());
      assertEquals(request.getCoverUrl(), response.getCoverUrl());
      assertEquals(request.getThumbnailUrl(), response.getThumbnailUrl());
      assertEquals(request.getTitle(), response.getTitle());
      return this;
    }

    public TestRunner searchNewsAndAssert(String query, int pageSize, int pageNumber, News news) throws Exception {
      SearchNewsResponse response = gson.fromJson(
          mvc
              .perform(get("/news/search")
                  .param("pageNumber", String.valueOf(pageNumber))
                  .param("pageSize", String.valueOf(pageSize))
                  .param("query", query)
                  .contentType(MediaType.APPLICATION_JSON))
              .andReturn().getResponse().getContentAsString(), SearchNewsResponse.class);
      assertEquals(1, response.getTotalCount().intValue());
      assertThat(news)
          .usingRecursiveComparison()
          .ignoringFields("id")
          .isEqualTo(response.getNews().get(0));
      return this;
    }

    public TestRunner searchNewsAndAssert(String query, int pageSize, int pageNumber, int count) throws Exception {
      SearchNewsResponse response = gson.fromJson(
          mvc
              .perform(get("/news/search")
                  .param("pageNumber", String.valueOf(pageNumber))
                  .param("pageSize", String.valueOf(pageSize))
                  .param("query", query)
                  .contentType(MediaType.APPLICATION_JSON))
              .andReturn().getResponse().getContentAsString(), SearchNewsResponse.class);
      assertEquals(count, response.getTotalCount().intValue());
      assertEquals(count, response.getNews().size());
      return this;
    }
  }
}
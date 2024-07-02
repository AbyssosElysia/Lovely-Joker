package com.elysiaptr.cemenghuiweb.web.service;

import com.elysiaptr.cemenghuiweb.web.dto.NewsDto;
import com.elysiaptr.cemenghuiweb.web.po.News;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsService {
    News saveNews(News news);

    News updateNews(Long id, NewsDto newsDetails);

    void deleteNews(Long id);

    News getNewsById(Long id);

    List<News> getAllNews();

    Page<News> getNewsByPage(int page, int size);

    List<News> searchNewsByTitle(String title);
    List<News> searchNewsById(Long id);
    List<News> searchNewsByIntroduction(String introduction);
    List<News> searchNewsByAuthor(String author);

}
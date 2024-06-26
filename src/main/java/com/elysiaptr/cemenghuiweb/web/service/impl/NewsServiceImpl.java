package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.News;
import com.elysiaptr.cemenghuiweb.web.repo.NewsRepository;
import com.elysiaptr.cemenghuiweb.web.service.NewsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Transactional
    @Override
    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    @Transactional
    @Override
    public News updateNews(Long id, News newsDetails) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id :: " + id));

        news.setImage(newsDetails.getImage());
        news.setTitle(newsDetails.getTitle());
        news.setContent(newsDetails.getContent());
        news.setAuthor(newsDetails.getAuthor());
        news.setIntroduction(newsDetails.getIntroduction());
        news.setCompany(newsDetails.getCompany());

        return newsRepository.save(news);
    }

    @Transactional
    @Override
    public void deleteNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id :: " + id));
        newsRepository.delete(news);
    }

    @Override
    public News getNewsById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id :: " + id));
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public Page<News> getNewsByPage(int page, int size) {
        return newsRepository.findAll(PageRequest.of(page, size));
    }

}
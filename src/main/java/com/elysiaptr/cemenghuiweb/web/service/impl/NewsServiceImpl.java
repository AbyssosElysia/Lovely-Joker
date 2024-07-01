package com.elysiaptr.cemenghuiweb.web.service.impl;

import com.elysiaptr.cemenghuiweb.web.dto.NewsDto;
import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.News;
import com.elysiaptr.cemenghuiweb.web.po.User;
import com.elysiaptr.cemenghuiweb.web.repo.CompanyRepository;
import com.elysiaptr.cemenghuiweb.web.repo.NewsRepository;
import com.elysiaptr.cemenghuiweb.web.service.NewsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @Transactional
    @Override
    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    @Transactional
    @Override
    public News updateNews(Long id, NewsDto newsDetails) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("News not found for this id :: " + id));
        if (newsDetails.getTitle() != null) {
            news.setTitle(newsDetails.getTitle());
        }
        if (newsDetails.getContent() != null) {
            news.setContent(newsDetails.getContent());
        }
        if (newsDetails.getAuthor() != null) {
            news.setAuthor(newsDetails.getAuthor());
        }
        if (newsDetails.getImage()!=null){
            news.setImage(newsDetails.getImage());
        }
        if (newsDetails.getIntroduction()!=null){
            news.setIntroduction(newsDetails.getIntroduction());
        }
        if (newsDetails.getCompany_id()!=null){
            news.setCompany(companyServiceImpl.getCompanyById(newsDetails.getCompany_id()));
        }
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
    @Override
    public List<News> searchNewsByTitle(String title){
        List<News> newsList =null;
        return newsList.stream()
                .filter(news -> news.getTitle().contains(title))
                .collect(Collectors.toList());
    }
    @Override
    public List<News> searchNewsByAuthor(String author){
        List<News> newsList =null;
        return newsList.stream()
                .filter(news -> news.getAuthor().contains(author))
                .collect(Collectors.toList());
    }
    @Override
    public List<News> searchNewsByIntroduction(String introduction){
        List<News> newsList =null;
        return newsList.stream()
                .filter(news -> news.getIntroduction().contains(introduction))
                .collect(Collectors.toList());
    }
    @Override
    public List<News> searchNewsById(long id){
        List<News> newsList =null;
        return newsList.stream()
                .filter(news -> news.getId().equals(id))
                .collect(Collectors.toList());
    }

}
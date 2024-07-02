package com.elysiaptr.cemenghuiweb.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.elysiaptr.cemenghuiweb.web.exception.ResourceNotFoundException;
import com.elysiaptr.cemenghuiweb.web.po.News;
import com.elysiaptr.cemenghuiweb.web.repo.NewsRepository;
import com.elysiaptr.cemenghuiweb.web.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class NewsServiceTest {

    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsServiceImpl newsServiceImpl;

    @Test
    void testDeleteNewsSuccess() {
        Long newsId = 1L;
        News news = new News();
        news.setId(newsId);

        when(newsRepository.findById(newsId)).thenReturn(Optional.of(news));

        newsServiceImpl.deleteNews(newsId);

        verify(newsRepository, times(1)).findById(newsId);
        verify(newsRepository, times(1)).delete(news);
    }

    @Test
    void testDeleteNewsNotFound() {
        Long newsId = 2L;

        when(newsRepository.findById(newsId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            newsServiceImpl.deleteNews(newsId);
        });

        String expectedMessage = "News not found for this id :: " + newsId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(newsRepository, times(1)).findById(newsId);
        verify(newsRepository, times(0)).delete(any(News.class));
    }
}


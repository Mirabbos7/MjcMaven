package com.mjc.school.repository.impl;

import com.mjc.school.repository.model.NewsModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NewsRepositoryTests {

    private NewsRepository newsRepository;

    @BeforeEach
    void setup() {
        newsRepository = NewsRepository.getInstance();
    }

    @Test
    void readAllTest(){
        List<NewsModel> newsModels = newsRepository.readAll();

        assertNotNull(newsModels);
        assertFalse(newsModels.isEmpty());
    }

    @Test
    void readById(){
        NewsModel news = NewsRepository.getInstance().readAll().get(0);
        NewsModel found = NewsRepository.getInstance().readBy(news.getId());

        assertNotNull(found);
        assertEquals(news.getId(), found.getId());
    }

    @Test
    void create_shouldAddNewsToList() {
        NewsRepository repo = new NewsRepository();
        int sizeBefore = repo.readAll().size();

        NewsModel newNews = new NewsModel(
                null, "Title", "Content", null, null, 1L
        );

        NewsModel created = repo.create(newNews);
        int sizeAfter = repo.readAll().size();

        assertEquals(sizeBefore + 1, sizeAfter);
        assertTrue(repo.readAll().contains(created));
    }


    @Test
    void update(){
        NewsModel newsModel = new NewsModel(1L, "title", "content", LocalDateTime.now(), LocalDateTime.now(), 1L);
        newsRepository.update(newsModel);
        assertTrue(newsRepository.readAll().contains(newsModel));
    }
}

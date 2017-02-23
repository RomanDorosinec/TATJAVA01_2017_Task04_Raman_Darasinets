package com.epam.task3.service;

import com.epam.task3.bean.News;
import com.epam.task3.service.exception.ServiceException;

import java.util.ArrayList;


/**
 * Interface that can be added and receive the news
 */
public interface NewsService {
    /**
     * News add's entered by user to databases
     */
    void addNews(News news) throws ServiceException;

    /**
     * Receive all news from the database
     */
    ArrayList<News> getNews(News news) throws ServiceException;
}

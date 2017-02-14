package com.epam.task3.service;

import com.epam.task3.bean.News;
import com.epam.task3.service.exception.ServiceException;

import java.util.ArrayList;


/**
 * Interface that can be added and receive the news
 */
public interface NewsService {
    /**
     * Method initialize and take resources, that application require
     */
    void init() throws ServiceException;


    /**
     * News add's entered by user to databases
     */
    void addNews(String category, String title, String author) throws ServiceException;

    /**
     * Receive all news from the database
     */
    ArrayList<News> getNews(String category, String title, String author) throws ServiceException;

    /**
     * Method frees resources
     */
    void destroy();
}

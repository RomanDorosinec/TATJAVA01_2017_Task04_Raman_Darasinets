package com.epam.task3.dao;

import com.epam.task3.bean.News;
import com.epam.task3.dao.exception.DAOException;

import java.util.ArrayList;

/**
 * Interface that can be added and receive the news
 */
public interface NewsDAO {
    /**
     * News add's entered by user
     */
    void addNews(News news) throws DAOException;

    /**
     * Receive all the news from the file
     */
    ArrayList<News> getNews(News news) throws DAOException;
}

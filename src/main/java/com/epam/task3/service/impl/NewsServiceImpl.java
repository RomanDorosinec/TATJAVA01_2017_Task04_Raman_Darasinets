package com.epam.task3.service.impl;

import com.epam.task3.bean.News;
import com.epam.task3.controller.NewsCategory;
import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.dao.factory.DAOFactory;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.InitializationException;
import com.epam.task3.service.exception.ServiceException;

import java.util.ArrayList;

/**
 * Class NewsServiceImpl which implements the interface methods of NewsService
 */
public class NewsServiceImpl implements NewsService {

    private static final String INCORRECT_NUMBER_PARAM = "Entered incorrect number of parameters";

    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final NewsDAO newsDAO = daoFactory.getNewDAO();

    @Override
    public void init() throws ServiceException {
        try {
            newsDAO.init();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method send checked news to DAO
     *
     * @param category category of news
     * @param title title of news
     * @param author author of news
     * @throws ServiceException if there are exceptions in Service layer
     */
    @Override
    public void addNews(String category, String title, String author) throws ServiceException {
        try {

            NewsCategory.valueOf(category.toUpperCase());
            newsDAO.addNews(getParamOfNewsForAdded(category, title, author));
        } catch (IllegalArgumentException | InitializationException e) {
            throw new ServiceException(e);
        } catch (DAOException e2) {
            throw new ServiceException(e2);
        }
    }

    /**
     * Method validate number of news parameters
     *
     * @param category category of news
     * @param title title of news
     * @param author author of news
     * @return news bean with all param
     * @throws InitializationException entered incorrect number of parameters
     */
    private News getParamOfNewsForAdded(String category, String title, String author) throws InitializationException {
        News news;
        if (category == null || title == null || author == null) {
            throw new InitializationException(INCORRECT_NUMBER_PARAM);
        }
        news = new News(category, title, author);
        return news;
    }

    /**
     * Method gets list of new from DAO
     *
     * @param category category of news
     * @param title title of news
     * @param author author of news
     * @return list of news
     * @throws ServiceException if there are exceptions in Service layer
     */
    @Override
    public ArrayList<News> getNews(String category, String title, String author) throws ServiceException {
        ArrayList<News> allNews;
        try {
            allNews = newsDAO.getNews(new News(category, title, author));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return allNews;
    }

    @Override
    public void destroy() {
        newsDAO.destroy();
    }
}
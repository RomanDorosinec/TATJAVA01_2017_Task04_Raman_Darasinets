package com.epam.task3.service.impl;

import com.epam.task3.bean.News;
import com.epam.task3.controller.NewsCategory;
import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.dao.factory.DAOFactory;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.InitializationException;
import com.epam.task3.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Class NewsServiceImpl which implements the interface methods of NewsService
 */
public class NewsServiceImpl implements NewsService {

    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class);

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
     * @param news
     * @throws ServiceException if there are exceptions in Service layer
     */
    @Override
    public void addNews(News news) throws ServiceException {
        try {

            NewsCategory.valueOf(news.getCategory().toUpperCase());
            checkParamsOfNewsForAdded(news.getCategory(), news.getTitle(), news.getAuthor());
            newsDAO.addNews(news);
        } catch (IllegalArgumentException | InitializationException e) {
            logger.error(e);
            throw new ServiceException(e);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method gets list of new from DAO
     *
     * @param news
     * @return list of news
     * @throws ServiceException if there are exceptions in Service layer
     */
    @Override
    public ArrayList<News> getNews(News news) throws ServiceException {
        ArrayList<News> allNews;
        try {
            allNews = newsDAO.getNews(news);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return allNews;
    }

    @Override
    public void destroy() {
        newsDAO.destroy();
    }

    /**
     * Method validate number of news parameters
     *
     * @param category category of news
     * @param title title of news
     * @param author author of news
     * @throws InitializationException entered incorrect number of parameters
     */
    private void checkParamsOfNewsForAdded(String category, String title, String author) throws InitializationException {
        if (category == null || title == null || author == null) {
            throw new InitializationException(INCORRECT_NUMBER_PARAM);
        }
    }
}
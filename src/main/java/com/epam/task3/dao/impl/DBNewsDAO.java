package com.epam.task3.dao.impl;

import com.epam.task3.bean.News;
import com.epam.task3.dao.utils.db.ConnectionPool;
import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.utils.db.excpetion.ConnectionPoolException;
import com.epam.task3.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class DBNewsDAO which implements the interface methods of NewsDAO
 */
public class DBNewsDAO implements NewsDAO {

    private static final Logger logger = LogManager.getLogger(DBNewsDAO.class);

    private static final String CATEGORY = "category";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";

    private final String INSERT_NEWS = "INSERT INTO news (category, title, author) VALUES (?, ?, ?)";
    private final String SELECT_NEWS = "SELECT * FROM news WHERE category LIKE ? AND title LIKE ? AND author LIKE ?";

    private ConnectionPool pool = ConnectionPool.getInstance();

    /**
     * Method create pool of connections, that are required to application
     */
    @Override
    public void init() throws DAOException {
        try {
            pool.initPoolData();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Method writes current news, got as argument, to database
     *
     * @param news news bean to write
     * @throws DAOException if there are exceptions in DAO
     */
    @Override
    public void addNews(News news) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(INSERT_NEWS);
            preparedStatement.setString(1, news.getCategory());
            preparedStatement.setString(2, news.getTitle());
            preparedStatement.setString(3, news.getAuthor());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    /**
     *
     * @param news news bean to find news in DB
     * @return list of news
     * @throws DAOException if there are exceptions in DAO
     */
    @Override
    public ArrayList<News> getNews(News news) throws DAOException {
        ArrayList<News> allNews = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = pool.takeConnection();
            preparedStatement = connection.prepareStatement(SELECT_NEWS);
            preparedStatement.setString(1, "%" + news.getCategory() + "%");
            preparedStatement.setString(2, "%" + news.getTitle() + "%");
            preparedStatement.setString(3, "%" + news.getAuthor() + "%");
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                allNews.add(new News(set.getString(CATEGORY), set.getString(TITLE), set.getString(AUTHOR)));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error(e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return allNews;
    }

    /**
     * Method closes all connections in connection pool
     */
    @Override
    public void destroy() {
        pool.clearConnectionQueue();
    }
}

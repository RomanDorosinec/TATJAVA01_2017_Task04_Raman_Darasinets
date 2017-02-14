package com.epam.task3.dao.factory;

import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.impl.DBNewsDAO;

/**
 * Class creates objects of DAO layer
 */
public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private NewsDAO NewsImpl = new DBNewsDAO();

    /**
     * Singleton implementation
     */
    public static DAOFactory getInstance() {
        return instance;
    }

    /**
     * Method returns object of NewsImpl
     *
     * @return object of DAO class
     */
    public NewsDAO getNewDAO() {
        return NewsImpl;
    }
}

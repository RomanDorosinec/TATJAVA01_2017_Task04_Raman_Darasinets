package com.epam.task3.dao.factory;

import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.DAOResourceManager;
import com.epam.task3.dao.impl.DBNewsDAO;
import com.epam.task3.dao.impl.DAOResourceManagerImpl;

/**
 * Class creates objects of DAO layer
 */
public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private NewsDAO NewsImpl = new DBNewsDAO();
    private DAOResourceManager DBResourceManagerImpl = new DAOResourceManagerImpl();

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

    public DAOResourceManager getDAOResourceManagerImpl() {
        return DBResourceManagerImpl;
    }
}

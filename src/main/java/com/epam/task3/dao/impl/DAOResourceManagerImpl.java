package com.epam.task3.dao.impl;

import com.epam.task3.dao.DAOResourceManager;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.dao.utils.db.ConnectionPool;
import com.epam.task3.dao.utils.db.excpetion.ConnectionPoolException;

/**
 *
 */
public class DAOResourceManagerImpl implements DAOResourceManager {

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
     * Method closes all connections in connection pool
     */
    @Override
    public void destroy() throws DAOException {
        try {
            pool.clearConnectionQueue();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }
}

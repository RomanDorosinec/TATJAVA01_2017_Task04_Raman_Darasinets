package com.epam.task3.dao;

import com.epam.task3.dao.exception.DAOException;

/**
 *
 */
public interface DAOResourceManager {
    void init() throws DAOException;

    void destroy() throws DAOException;
}

package com.epam.task3.service.impl;

import com.epam.task3.dao.DAOResourceManager;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.dao.factory.DAOFactory;
import com.epam.task3.service.ResourceManagerService;
import com.epam.task3.service.exception.ServiceException;

/**
 *
 */
public class ResourceManagerServiceImpl implements ResourceManagerService {
    @Override
    public void init() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOResourceManager daoResourceManager = daoFactory.getDAOResourceManagerImpl();
        try {
            daoResourceManager.init();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void destroy() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOResourceManager daoResourceManager = daoFactory.getDAOResourceManagerImpl();
        try {
            daoResourceManager.destroy();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
